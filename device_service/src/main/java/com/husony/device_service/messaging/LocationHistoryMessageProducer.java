package com.husony.device_service.messaging;

import com.husony.device_service.dto.LocationHistoryMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationHistoryMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public LocationHistoryMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(LocationHistoryMessage locationHistoryMessage){
        rabbitTemplate.convertAndSend("locationHistoryQueue",locationHistoryMessage);
    }
}
