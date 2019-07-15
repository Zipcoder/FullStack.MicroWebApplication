package com.zipcodewilmington.videoproject.controllers;

import com.zipcodewilmington.videoproject.models.Comment;
import com.zipcodewilmington.videoproject.models.User;
import com.zipcodewilmington.videoproject.services.CommentService;
import com.zipcodewilmington.videoproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> index(){
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }



}
