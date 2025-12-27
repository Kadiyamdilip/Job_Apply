package com.auto.Job_Apply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JobApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplyApplication.class, args);
	}

}
