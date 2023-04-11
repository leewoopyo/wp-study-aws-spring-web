package com.woopi.study.wpstudyawsspringweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Jpa Auditing 활성화
@SpringBootApplication
public class WpStudyAwsSpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpStudyAwsSpringWebApplication.class, args);
	}

}
