package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

//AuthController.java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
 @Autowired
 private UserService userService;
 
 @PostMapping("/register")
 public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
     userService.registerNewUser(userDTO);
     return ResponseEntity.ok("User registered successfully");
 }
 
 @PostMapping("/login")
 public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserDTO userDTO) {
     return ResponseEntity.ok(userService.authenticateUser(userDTO));
 }
}