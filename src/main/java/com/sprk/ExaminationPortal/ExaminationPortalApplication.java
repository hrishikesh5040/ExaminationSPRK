package com.sprk.ExaminationPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sprk.ExaminationPortal.Entity")
public class ExaminationPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminationPortalApplication.class, args);
	}

}
