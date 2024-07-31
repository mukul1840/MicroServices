package com.rabbitmq.RabbitMqDemo.consumer;

import com.rabbitmq.RabbitMqDemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSonConsumer {

    private static  final Logger logger= LoggerFactory.getLogger(RabbitMQJSonConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public  void consumeJsonMessage(User user){
logger.info(String.format("Received JSON Message--> %s",user.toString()));
    }
}
