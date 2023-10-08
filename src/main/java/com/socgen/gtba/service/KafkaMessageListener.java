package com.socgen.gtba.service;

import com.socgen.gtba.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "lucid-topic2", groupId = "lucid-grp-2")
    public void consume1(Customer customer){
        log.info("consumer1 consumed the message: {}", customer);
    }


    @KafkaListener(topics = "lucid-topic1", groupId = "lucid-grp")
    public void consume2(String message){
        log.info("consumer2 consumed the message: {}", message);
    }
    @KafkaListener(topics = "lucid-topic1", groupId = "lucid-grp")
    public void consume3(String message){
        log.info("consumer3 consumed the message: {}", message);
    }
    @KafkaListener(topics = "lucid-topic1", groupId = "lucid-grp")
    public void consume4(String message){
        log.info("consumer4 consumed the message: {}", message);
    }
}
