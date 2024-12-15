package com.sube.plus.apaseo.sube_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.sube.plus.apaseo.sube_back.converter", "com.sube.plus.apaseo.sube_back"})
@RestController
@EnableMongoAuditing
public class SubeBackApplication {

	@GetMapping("/message")
	public String message () {
		return "Yes";
	}
	public static void main(String[] args) {
		SpringApplication.run(SubeBackApplication.class, args);
	}

}
