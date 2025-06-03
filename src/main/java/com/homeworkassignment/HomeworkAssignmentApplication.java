package com.homeworkassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HomeworkAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkAssignmentApplication.class, args);
	}

}
