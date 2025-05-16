package com.producer.producer.controller;

import com.producer.producer.service.KafkaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class KafkaController {


    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/produce")
    public ResponseEntity<?> produceValue() {
        for (int i = 1; i <= 100000; i++) {
            this.kafkaService.produce("Coordinates: "+ i + " (lat: " + Math.round(Math.random() * 100) + " , long: " + Math.round(Math.random() * 100) + " " + ")");
        }
        return new ResponseEntity<>(Map.of("Message", "Produced"), HttpStatus.OK);

    }

}
