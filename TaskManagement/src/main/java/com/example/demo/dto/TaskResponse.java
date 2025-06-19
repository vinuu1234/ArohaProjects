package com.example.demo.dto;

import lombok.Data;

@Data
public class TaskResponse {
	private Long id;
	private String description;
	private boolean completed;

}