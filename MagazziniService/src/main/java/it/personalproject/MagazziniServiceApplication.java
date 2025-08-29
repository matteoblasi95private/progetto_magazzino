package it.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.personalproject.magazzini.repositories"})
@EntityScan(basePackages = {"it.personalproject.magazzini.entities"})
@EnableAutoConfiguration
public class MagazziniServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazziniServiceApplication.class, args);
	}

}
