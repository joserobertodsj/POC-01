package com.invillia.poc01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Locale;

@SpringBootApplication
public class Poc01Application {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SpringApplication.run(Poc01Application.class, args);
	}

}
