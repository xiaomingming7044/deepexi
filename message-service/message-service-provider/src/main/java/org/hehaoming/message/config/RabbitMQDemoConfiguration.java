package org.hehaoming.message.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitMQDemoConfiguration.Receiver.class)
public class RabbitMQDemoConfiguration {
    @Bean
    public Queue test() {
        return new Queue("test.queue");
    }


    @RabbitListener(id="UpdateBotReceiver", queues = "test.queue", containerFactory="rabbitListenerContainerFactory")
    public class Receiver {

        @RabbitHandler
        public void process(String str) {
            System.out.println(str);
        }
    }

    @Data
    @ToString
    class ContentDto {
        private Long id;
        private String content;
    }
}
