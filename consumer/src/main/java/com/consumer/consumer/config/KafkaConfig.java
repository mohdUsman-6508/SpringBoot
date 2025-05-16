package com.consumer.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener(topics = Constants.PRODUCER_TOPIC, groupId = Constants.GROUP_ID)
    public void produce(String value) {
        System.out.println(value);
    }
}
