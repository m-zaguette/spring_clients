package com.example.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClientsApplication {

	@Value("${spring.application.name}")
	private String _applicationName;

	@GetMapping("/")
	public String homePage(){
		return _applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientsApplication.class, args);
	}
}
