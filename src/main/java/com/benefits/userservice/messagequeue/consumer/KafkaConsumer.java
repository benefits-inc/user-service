package com.benefits.userservice.messagequeue.consumer;

import com.benefits.userservice.domain.users.business.UserBusiness;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final UserBusiness userBusiness;
    private final ObjectMapper objectMapper;
    //updateProductStockCount

    @KafkaListener(topics = "lastLogin")
    public void orderListen(String kafkaMessage){

        Long userId = null;

        try {
            userId = objectMapper.readValue(kafkaMessage, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        userBusiness.updateLastLoginAt(userId);
    }
}
