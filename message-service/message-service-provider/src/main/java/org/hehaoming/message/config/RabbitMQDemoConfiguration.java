package org.hehaoming.message.config;

import lombok.Data;
import lombok.ToString;
import org.hehaoming.message.domain.query.AddMessage;
import org.hehaoming.message.service.MessageService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private MessageService messageService;

    @RabbitListener(id="UpdateBotReceiver", queues = "test.queue", containerFactory="rabbitListenerContainerFactory")
    public class Receiver {

        @RabbitHandler
        public void process(String str) {
            String[] s = str.split("_",2);
            messageService.addMessage(new AddMessage(Integer.parseInt(s[0]),s[1]));
        }
    }

    @Data
    @ToString
    class ContentDto {
        private Long id;
        private String content;
    }

    public static void main(String[] args) {

    }
}
