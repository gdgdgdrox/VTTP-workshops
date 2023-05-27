package attempt1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication{

	// @Autowired
	// private FeedRepository feedRepo;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
