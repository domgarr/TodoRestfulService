package com.example.todorestful;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class TodoRestfulApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TodoRestfulApplication.class, args);
	}	
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder(12);
	}
}
