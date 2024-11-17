package com.example.runnerz;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.runnerz.run.Location;
import com.example.runnerz.run.Run;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class); 

	public static void main(String[] args) {
	
		SpringApplication.run(Application.class, args);

			// In Spring Boot, there is collection of all the classes in the application called the Application Context and we can ask it for a particular class
			// a Spring Bean is an instance of a class. Make sure all code is in the main package. 
			// Package by layer (MVC), or package by feature (packaged private by default)
		}

		// A CommandLineRunner runs after an application has started and more improtantly after the Application Context has been created  
		@Bean
		CommandLineRunner runner() {
			return args -> {
				Run run =  new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
				log.info("Run: "+run);
			};
		}

}
  