package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;

//TaskRepository.java
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByUser(User user);

	Optional<Task> findByIdAndUser(Long id, User user);
}