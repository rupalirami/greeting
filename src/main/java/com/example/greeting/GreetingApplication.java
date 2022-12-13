package com.example.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

// the SpringBootApplication with exclude is to say not to connect to the database
// remove it when you need to connect to the database

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@ComponentScan({"com.example.greeting"})
public class GreetingApplication {


	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}


}