package com.example.slotselection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.slotselection.Repository")
public class SlotselectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlotselectionApplication.class, args);
	}

}
