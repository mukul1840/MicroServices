package com.rabbitmq.RabbitMqDemo.publisher;


import com.rabbitmq.RabbitMqDemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${exchange.name}")
    private String exchange;

    @Value("${rabbitMq.json.routing.key}")
    private String jsonRoutingKey;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    private RabbitTemplate rabbitTemplate;
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        logger.info(String.format("Json Message sent --> %s", user.toString()));

        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
