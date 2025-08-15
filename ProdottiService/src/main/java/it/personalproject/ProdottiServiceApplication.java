package it.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.personalproject.prodotti.repositories"})
@EntityScan(basePackages = {"it.personalproject.prodotti.entities"})
@EnableAutoConfiguration
public class ProdottiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdottiServiceApplication.class, args);
	}

}
