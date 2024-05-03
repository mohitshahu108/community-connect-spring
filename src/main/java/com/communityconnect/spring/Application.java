package com.communityconnect.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.communityconnect.spring.service.S3Service;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Bean
	// CommandLineRunner runner(S3Service s3Service){
	// 	return args -> {
	// 		s3Service.putObject("community-connect-files", "foo", "Hello World".getBytes());
	// 		byte [] bytes = s3Service.getObject("community-connect-files", "foo");
	// 		System.out.println("Horray" + new String(bytes));
	// 	};
	// }
}
