package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private static final Logger logger = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";
        String restApiUrl = "https://stream.wikimedia.org/v2/stream/page-create";

        //read relatime data use eventsource
        //https://stream.wikimedia.org/v2/stream/page-create

       EventHandler eventHandler = new WIkimediaChangesHandler(kafkaTemplate, topic);

       EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(restApiUrl));
       EventSource eventSource  = builder.build();
       eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}