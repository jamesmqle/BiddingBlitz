package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.backend")
@EnableScheduling
public class BiddingBlitzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiddingBlitzApplication.class, args);
	}

}
