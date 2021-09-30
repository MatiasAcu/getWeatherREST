package com.getWeatherREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GetWeatherRESTApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetWeatherRESTApplication.class, args);

	}

}
