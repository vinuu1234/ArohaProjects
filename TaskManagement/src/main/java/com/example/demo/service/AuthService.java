package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exceptions.UsernameAlreadyExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

//AuthService.java
@Service
public class AuthService {
 
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private PasswordEncoder passwordEncoder;
 
 @Autowired
 private JwtTokenProvider tokenProvider;
 
 public String register(RegisterRequest request) {
	    if (userRepository.existsByUsername(request.getUsername())) {
	        throw new UsernameAlreadyExistsException("Username '" + request.getUsername() + "' already exists");
	    }
	    
	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
	    
	    userRepository.save(user);
	    
	    return tokenProvider.generateToken(user);
	}
 
 public String login(LoginRequest request) {
     User user = userRepository.findByUsername(request.getUsername())
         .orElseThrow(() -> new RuntimeException("User not found"));
         
     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
         throw new RuntimeException("Invalid password");
     }
     
     return tokenProvider.generateToken(user);
 }
}