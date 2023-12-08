package com.example.viewcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ViewControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ViewControllerApplication.class, args);
    }
}
