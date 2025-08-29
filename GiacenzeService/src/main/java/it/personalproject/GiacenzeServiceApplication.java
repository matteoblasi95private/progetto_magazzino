package it.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.personalproject.giacenze.repositories"})
@EntityScan(basePackages = {"it.personalproject.giacenze.entities"})
@EnableAutoConfiguration
public class GiacenzeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiacenzeServiceApplication.class, args);
	}

}
