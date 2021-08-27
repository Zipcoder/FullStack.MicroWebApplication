package com.example.demo.controller;

import com.example.demo.models.Profile;
import com.example.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/profile")
public class ProfileController {

    @Autowired
    ProfileService service;

    @PostMapping(value = "/register")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(service.createProfile(profile), HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Profile> findProfileById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Profile>> findAllProfiles() {
        return new ResponseEntity<>(service.findAllProfiles(), HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Profile> login(@RequestBody String username, @RequestBody String password) {
        return new ResponseEntity<>(service.login(username, password), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(service.update(profile), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Profile> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteProfileById(id), HttpStatus.OK);
    }
}
