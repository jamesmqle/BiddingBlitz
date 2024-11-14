package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.backend")
public class BiddingBlitzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiddingBlitzApplication.class, args);
	}

}
