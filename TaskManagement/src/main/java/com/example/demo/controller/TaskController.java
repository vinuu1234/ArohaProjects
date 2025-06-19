package com.example.demo.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskRequest;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;




//TaskController.java
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
 
 @Autowired
 private TaskService taskService;
 
 @GetMapping
 public List<Task> getUserTasks(Authentication authentication) {
     User user = (User) authentication.getPrincipal();
     return taskService.getUserTasks(user);
 }
 
 @PostMapping
 public Task createTask(@RequestBody TaskRequest
		 request, Authentication authentication) {
     User user = (User) authentication.getPrincipal();
     return taskService.createTask(request, user);
 }
 
 @PatchMapping("/{taskId}/complete")
 public Task markTaskComplete(@PathVariable Long taskId, Authentication authentication) {
     User user = (User) authentication.getPrincipal();
     return taskService.updateTaskStatus(taskId, true, user);
 }
 
 @DeleteMapping("/{taskId}")
 public ResponseEntity<?> deleteTask(@PathVariable Long taskId, Authentication authentication) {
     User user = (User) authentication.getPrincipal();
     taskService.deleteTask(taskId, user);
     return ResponseEntity.noContent().build();
 }
}