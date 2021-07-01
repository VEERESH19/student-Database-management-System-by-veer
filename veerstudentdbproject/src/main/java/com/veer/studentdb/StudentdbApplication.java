package com.veer.studentdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.veer.studentdb")
public class StudentdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentdbApplication.class, args);
	}
}
