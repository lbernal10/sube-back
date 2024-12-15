package com.sube.plus.apaseo.sube_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.sube.plus.apaseo.sube_back.converter", "com.sube.plus.apaseo.sube_back"})
@RestController
@EnableMongoAuditing
@Configuration
@EnableAutoConfiguration
public class SubeBackApplication implements CommandLineRunner {

	@Value("${app.secret}") // Correcto si la propiedad es app.secret
	private String appSecret;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/message")
	public String message () {
		System.out.println("Valor de app.secret: " + appSecret);


		return "Yes";
	}
	public static void main(String[] args) {
		SpringApplication.run(SubeBackApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Valor de app.secret: " + appSecret);
		String password = "12345";
		for (int i = 0; i < 3; i++) {
			String passwordEncriptada = passwordEncoder.encode(password);
			System.out.println(passwordEncriptada);
		}
	}
}
