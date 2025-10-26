package it.personalproject.clienti.test.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import it.personalproject.clienti.entities.TisClienti;
import it.personalproject.clienti.repositories.ClientiRepository;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Testcontainers
class ClientiRepositoryIT {
	
	@LocalServerPort
	private Integer port;
	
	@Autowired
	ClientiRepository clientiRepository;
	
	@Container
	static MSSQLServerContainer<?> sqlserver = new MSSQLServerContainer<>(
			"mcr.microsoft.com/mssql/server:2022-latest"
	).acceptLicense().withPassword("Str0ngP@ssw0rd!");
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () -> sqlserver.getJdbcUrl() + ";encrypt=true;trustServerCertificate=true");
		registry.add("spring.datasource.username", sqlserver::getUsername);
		registry.add("spring.datasource.password", sqlserver::getPassword);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
	}
	
	@BeforeEach
	void setUpTest() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void salvaCliente() {
		
		var cliente = new TisClienti();
		cliente.setCodiceFiscale("ABC");
		cliente.setNome("FIRSTNAME-ITTEST1");
		cliente.setCognome("SECONDNAME-ITTEST1");
		cliente.setEmail("ittest1@ittest1");
		cliente.setTelefono("1234");
		cliente.setIndirizzo("INDIRIZZO-ITTEST1");
		cliente.setCitta("CITTA-ITTEST1");
		cliente.setCap("00040");
		cliente.setPaese("IT");
		clientiRepository.save(cliente);
		
		given()
	      .when()
	      .get("/clienti/all")
	      .then()
	      .statusCode(200)
	      .body(".", hasSize(1));
	  }

}
