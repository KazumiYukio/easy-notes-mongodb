package com.example.easynotesmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class EasyNotesMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesMongoApplication.class, args);
	}

}
