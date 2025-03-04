package com.firewall.linkedinlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LinkedinliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkedinliteApplication.class, args);
	}

}
