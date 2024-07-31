package com.rabbitmq.RabbitMqDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String userId;
    private String name;
    private  String email;
    private String about;

}