package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener(
			final LocalValidatorFactoryBean factory) {
		return new ValidatingMongoEventListener(factory);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
