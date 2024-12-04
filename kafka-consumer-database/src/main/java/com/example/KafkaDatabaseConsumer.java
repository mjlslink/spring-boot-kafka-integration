package com.example;

import com.example.entity.WikimediaData;
import com.example.repository.WikimediaJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private final WikimediaJPARepository wikimediaJPARepository;

    public KafkaDatabaseConsumer(WikimediaJPARepository wikimediaJPARepository) {
        this.wikimediaJPARepository=wikimediaJPARepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage) {
        logger.info(String.format(" event message received ->  %s",eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEvent(eventMessage);
        wikimediaJPARepository.save(wikimediaData);

       logger.info(String.format(" event message saved ->  %s",wikimediaData.getId()));

    }
}
