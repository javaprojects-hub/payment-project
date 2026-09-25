package com.axc.solidprinciples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.axc.solidprinciples", "com.dto" }) 
public class SolidprinciplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolidprinciplesApplication.class, args);
	}

}
