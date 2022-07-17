package com.example.publisher.services;


import com.example.publisher.config.MessageConfig;
import com.example.publisher.dto.User;
import com.example.publisher.json.JsonSchemaValidation;
import com.example.publisher.publisher.UserPub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.everit.json.schema.ValidationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.UUID;

@RestController
public class UserServices {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) throws FileNotFoundException, JsonProcessingException {
        try {
            UserPub userPub = new UserPub();
            userPub.sendMessage(user, template);
            return "200";
        } catch (ValidationException e) {
            System.out.println("validation failed");
            e.printStackTrace();
            return "Validation failed";
        }
    }
}
