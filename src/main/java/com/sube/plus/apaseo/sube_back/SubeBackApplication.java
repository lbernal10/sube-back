package com.sube.plus.apaseo.sube_back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.sube.plus.apaseo.sube_back.converter", "com.sube.plus.apaseo.sube_back"})
@RestController
@EnableMongoAuditing
@Configuration
@EnableAutoConfiguration
@Slf4j
public class SubeBackApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/message")
	public String message () {
		return "Yes";
	}
	public static void main(String[] args) {
		SpringApplication.run(SubeBackApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
		log.info("Zona horaria configurada: " + TimeZone.getDefault().getID());
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		for (int i = 0; i < 3; i++) {
			String passwordEncriptada = passwordEncoder.encode(password);
			System.out.println(passwordEncriptada);
		}
	}
}
