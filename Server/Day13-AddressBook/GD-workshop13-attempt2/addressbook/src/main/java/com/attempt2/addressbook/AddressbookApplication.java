package com.attempt2.addressbook;

import java.io.File;

import javax.crypto.spec.DESKeySpec;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressbookApplication {
	public static String dataDir = "";

	public static void main(String[] args) {
		SpringApplication.run(AddressbookApplication.class, args);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		if (appArgs.containsOption("dataDir")){
			dataDir = appArgs.getOptionValues("dataDir").get(0);
			File file = new File (dataDir);
			if (!file.exists()){
				file.mkdirs();
			}
		}
		else{
			System.out.println("Please enter a directory name");
			System.exit(1);
		}
	}

}
