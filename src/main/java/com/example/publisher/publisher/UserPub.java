package com.example.publisher.publisher;

import com.example.publisher.config.MessageConfig;
import com.example.publisher.dto.User;
import com.example.publisher.json.JsonSchemaValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.UUID;


public class UserPub {

    public void sendMessage(User user, RabbitTemplate template) throws JsonProcessingException, FileNotFoundException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);
        JsonSchemaValidation validator = new JsonSchemaValidation();
        validator.validateJson(json);
        user.setUserId(UUID.randomUUID().toString());
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, user);
    }
}
