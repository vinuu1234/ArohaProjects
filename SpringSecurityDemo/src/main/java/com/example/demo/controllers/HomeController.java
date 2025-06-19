package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/user")
	public String user()
	{
		return "Hello user";
	}
	@GetMapping("/admin")
	public String admin()
	{
		return "Hello admin";
	}

}
