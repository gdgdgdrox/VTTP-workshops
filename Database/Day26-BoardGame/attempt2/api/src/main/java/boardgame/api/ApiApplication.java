package boardgame.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ApiApplication {

	@Bean
	public ObjectMapper createObjectMapper(){
		return new ObjectMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}



}
