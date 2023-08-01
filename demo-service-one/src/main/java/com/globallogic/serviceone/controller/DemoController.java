package com.globallogic.serviceone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope
public class DemoController {
	
	@Value("${product.name}")
	private String name;
	
	@Value("${product.price}")
	private String price;
	
	@GetMapping("/details")
	public String getDetails()
	{
		return "product Name:"+name+"\nPrice:"+price;
	}
	
}
