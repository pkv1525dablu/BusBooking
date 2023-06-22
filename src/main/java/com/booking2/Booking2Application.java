package com.booking2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Booking2Application {
	 @Bean
	 public ModelMapper modelMapper()
	 { return new ModelMapper(); }

	public static void main(String[] args) {
		SpringApplication.run(Booking2Application.class, args);
	}

}
