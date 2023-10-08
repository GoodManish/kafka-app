package com.socgen.gtba.controller;

import com.socgen.gtba.dto.Customer;
import com.socgen.gtba.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {
    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMsg(@PathVariable String message){
        try {
            for (int i = 0; i < 10000; i++) {
                publisher.sendMsg(message +"-"+i);
            }
            return ResponseEntity.ok("message published successfully!!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody Customer customer){
        publisher.sendEventsToTopic(customer);
    }

}
