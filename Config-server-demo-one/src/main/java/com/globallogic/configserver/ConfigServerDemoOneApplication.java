package com.globallogic.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerDemoOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerDemoOneApplication.class, args);
	}

}
