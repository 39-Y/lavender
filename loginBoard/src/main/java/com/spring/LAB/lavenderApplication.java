package com.spring.LAB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class lavenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(lavenderApplication.class, args);
	}

}
