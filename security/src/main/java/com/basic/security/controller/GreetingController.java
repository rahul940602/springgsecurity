package com.basic.security.controller;



import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	
	@GetMapping("/hello")
	public String getGreeting() {
		
		return "Hello";
		
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String userDetails() {
		
		return "Hello User!";
		
	}
	
	@PreAuthorize("hasRole('AMIN')")
	@GetMapping("/admin")
	public String adminDetails() {
		
		return "Hello Admin!";
		
	}

}
