package com.husony.maintenance_repair_service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public DeviceMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(DeviceMessage deviceMessage){
        rabbitTemplate.convertAndSend("deviceQueue",deviceMessage);
    }
}
