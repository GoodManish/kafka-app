package com.socgen.gtba.service;

import com.socgen.gtba.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMsg(String msg) {
        CompletableFuture<SendResult<String, Object>> future = template.send("lucid-topic1", msg);
        future.whenComplete((result, ex) -> {
            if(ex==null)
                log.info("Sent message=[" + msg +"] with offset=[" + result.getRecordMetadata().offset() + "]");
            else
                log.error("Unable to send message=[" + msg + "] due to : " +ex.getMessage());
        });
    }

    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("lucid-topic2", customer);
            future.whenComplete((result, ex) -> {
                if(ex==null)
                    log.info("Sent customer=[" + customer +"] with offset=[" + result.getRecordMetadata().offset() + "]");
                else
                    log.error("Unable to send customer=[" + customer + "] due to : " +ex.getMessage());
            });
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
        }
    }
}
