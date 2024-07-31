package com.rabbitmq.RabbitMqDemo.conntroller;

import com.rabbitmq.RabbitMqDemo.dto.User;
import com.rabbitmq.RabbitMqDemo.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public RabbitController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
     rabbitMQJsonProducer.sendJsonMessage(user);
     return ResponseEntity.ok("Json Message Sent to RabbitMq Server ....");
    }
}
