package com.tnl.coindeskapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoindeskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoindeskApiApplication.class, args);
	}

}
