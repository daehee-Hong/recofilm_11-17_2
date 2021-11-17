package com.example.recofilm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class RecofilmApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecofilmApplication.class, args);
	}

}
