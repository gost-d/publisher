package com.example.publisher.services;



import com.example.publisher.dto.User;
import com.example.publisher.publisher.UserPub;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.everit.json.schema.ValidationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;


@RestController
public class UserServices {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) throws FileNotFoundException, JsonProcessingException {
        try {
            UserPub userPub = new UserPub();
            userPub.sendMessage(user, template);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ValidationException e) {
            System.out.println("validation failed");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
