package com.example.security;

import com.example.security.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class BasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSecurityApplication.class, args);
	}

}
