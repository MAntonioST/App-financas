package com.app.financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@SpringBootApplication
@EnableWebMvc
public class FinancasApplication implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	   registry.addMapping("/**").allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS");
	}

	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(FinancasApplication.class, args);
	}
}