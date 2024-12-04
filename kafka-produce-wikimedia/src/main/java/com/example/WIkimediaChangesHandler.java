package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class WIkimediaChangesHandler implements EventHandler {

    private static final Logger loggrr = LoggerFactory.getLogger(WIkimediaChangesHandler.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public WIkimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        kafkaTemplate.send("wikimedia_recentchange", messageEvent.getData());
        loggrr.info(String.format("Event datss -> %s ", s));

    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
