package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
(
		scanBasePackages = {"com.luv2code.springcoredemo",
				"com.luv2code.util"}
)

public class SpringCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}

}
