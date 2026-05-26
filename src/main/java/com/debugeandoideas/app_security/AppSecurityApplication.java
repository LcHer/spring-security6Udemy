package com.debugeandoideas.app_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity //esto ya es opcional en la version 6 de spring security
public class AppSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSecurityApplication.class, args);
	}

}
