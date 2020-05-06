package org.hehaoming.user.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDemoServiceImpl {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void produce(String str) {
        rabbitTemplate.convertAndSend("test.queue", str);
    }

}
