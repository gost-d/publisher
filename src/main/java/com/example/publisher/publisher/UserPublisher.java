package com.example.publisher.publisher;


import com.example.publisher.config.MessageConfig;
import com.example.publisher.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, user);
        return "200";
    }
}
