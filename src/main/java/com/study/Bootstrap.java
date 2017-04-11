package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:application.properties",
		"application-db.properties"})
@SpringBootApplication
public class Bootstrap {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}
}
