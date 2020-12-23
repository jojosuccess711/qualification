package com.bdjbd.baseweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableAutoConfiguration
@ComponentScans(value = {@ComponentScan("com.*"), @ComponentScan("com.bdjbd.controller"), @ComponentScan("com.bdjbd.service")})
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class BaseWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseWebApplication.class, args);
	}

}
