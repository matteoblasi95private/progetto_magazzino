package it.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.personalproject.clienti.repositories"})
@EntityScan(basePackages = {"it.personalproject.clienti.entities"})
@EnableAutoConfiguration
public class ClientiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientiServiceApplication.class, args);
	}

}
