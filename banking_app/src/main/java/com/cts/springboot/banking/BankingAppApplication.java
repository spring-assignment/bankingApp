package com.cts.springboot.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
