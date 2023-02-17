package com.example.backendspatiali;

import com.example.backendspatiali.config.SecretsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecretsProperties.class)
public class BackendSpatialiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSpatialiApplication.class, args);
	}

}
