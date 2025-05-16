package com.producer.producer.service;

import com.producer.producer.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {


    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean produce(String value) {
        this.kafkaTemplate.send(Constants.TOPIC_NAME, value);
        return true;
    }
}
