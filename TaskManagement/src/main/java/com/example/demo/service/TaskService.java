package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;

//TaskService.java
@Service
public class TaskService {
 
 @Autowired
 private TaskRepository taskRepository;
 
 public Task createTask(TaskRequest request, User user) {
     Task task = new Task();
     task.setDescription(request.getDescription());
     task.setCompleted(false);
     task.setUser(user);
     
     return taskRepository.save(task);
 }
 
 public List<Task> getUserTasks(User user) {
     return taskRepository.findByUser(user);
 }
 
 public Task updateTaskStatus(Long taskId, boolean completed, User user) {
     Task task = taskRepository.findByIdAndUser(taskId, user)
         .orElseThrow(() -> new RuntimeException("Task not found"));
         
     task.setCompleted(completed);
     return taskRepository.save(task);
 }
 
 public void deleteTask(Long taskId, User user) {
     Task task = taskRepository.findByIdAndUser(taskId, user)
         .orElseThrow(() -> new RuntimeException("Task not found"));
         
     taskRepository.delete(task);
 }
}