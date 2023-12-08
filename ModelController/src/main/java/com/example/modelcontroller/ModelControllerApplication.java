package com.example.modelcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EntityScan(basePackages =  "com.example.modelcontroller.entity")
@EnableJdbcRepositories("com.example.modelcontroller.model")
public class ModelControllerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModelControllerApplication.class, args);
	}

}
