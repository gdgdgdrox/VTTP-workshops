package com.preparation.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CryptoApplication {

	@Bean
	public RestTemplate restTemplateGenerator(){
		return new RestTemplate();
		
	}
	public static void main(String[] args) {
		SpringApplication.run(CryptoApplication.class, args);
	}

}
