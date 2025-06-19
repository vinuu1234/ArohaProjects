package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;

//AuthController.java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
private AuthService authService;

@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
   String token = authService.register(request);
   return ResponseEntity.ok(token);
}

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
   String token = authService.login(request);
   return ResponseEntity.ok(token);
}
}