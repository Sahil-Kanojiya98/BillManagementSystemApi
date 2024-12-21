package com.BillGenrationsSystem.BillManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class BillManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillManagementSystemApplication.class, args);
	}

}
