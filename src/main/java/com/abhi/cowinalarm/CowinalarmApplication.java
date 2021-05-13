package com.abhi.cowinalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CowinalarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CowinalarmApplication.class, args);
	}

}
