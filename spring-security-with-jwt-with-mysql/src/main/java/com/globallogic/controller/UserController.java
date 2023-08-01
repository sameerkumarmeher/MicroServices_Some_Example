package com.globallogic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_BUYER')")
	@GetMapping("/hello")
	public String hello() {
		return "AnyOne can access this";
	}
	
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@PostMapping("/seller")
	public String sellerAccess() {
		return "Only Seller can view this";
	}
	
	@PreAuthorize("hasRole('ROLE_BUYER')")
	@PostMapping("/buyer")
	public String buyerAccess() {
		return "Only buyer can view this";
	}

}
