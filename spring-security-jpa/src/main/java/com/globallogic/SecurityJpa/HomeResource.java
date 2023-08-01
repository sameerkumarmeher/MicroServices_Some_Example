package com.globallogic.SecurityJpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	@GetMapping("/")
	public String home()
	{
		return "Welcome to home";
	}
	
	@GetMapping("/user")
	public String user()
	{
		return "Welcome to user";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "Welcome to admin";
	}
	
}
