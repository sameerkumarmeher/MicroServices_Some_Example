package com.globallogic.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	
	Log log = LogFactory.getLog(EmployeeController.class);
	
	
	@GetMapping("/message")
	public String getMessage() {
		log.error("this is error info");
		log.warn("warning info");
		log.info("this is info");
		
		return "hello logger";
	}
}
