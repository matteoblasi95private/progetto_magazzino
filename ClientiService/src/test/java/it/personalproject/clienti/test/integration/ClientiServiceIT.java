package it.personalproject.clienti.test.integration;

import org.junit.jupiter.api.*;
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
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Testcontainers
public class ClientiServiceIT {
	
	@LocalServerPort
	Integer port;
	
	@Container
	static MSSQLServerContainer<?> sqlserver = new MSSQLServerContainer<>(
			"mcr.microsoft.com/mssql/server:2022-latest").acceptLicense().withPassword("Str0ngP@ssw0rd!");
	
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () -> sqlserver.getJdbcUrl() + ";encrypt=true;trustServerCertificate=true");
		registry.add("spring.datasource.username", sqlserver::getUsername);
		registry.add("spring.datasource.password", sqlserver::getPassword);
		registry.add("spring.jpa.show-sql", () -> true);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
	}
	
	@BeforeEach
	void setupRestAssured() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	@Test
	void creaCliente_ok() {
		
		var payload = """
				{
				"codiceFiscale": "ABCDEF12G34H567I",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "aaa@bbb",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(201)
		.body("id", notNullValue())
		.body("codiceFiscale", equalTo("ABCDEF12G34H567I"))
		.body("nome", equalTo("Mario"))
		.body("cognome", equalTo("Rossi"))
		.body("email", equalTo("aaa@bbb"))
		.body("telefono", equalTo("1234"))
		.body("indirizzo", equalTo("Via Roma 1"))
		.body("citta", equalTo("Roma"))
		.body("cap", equalTo("00042"))
		.body("paese", equalTo("Italia"));
		
	}
	
	@Test
	void creaCliente_email_malformed_status400() {
		
		var payload = """
				{
				"codiceFiscale": "ABCDEF12G34H567I",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "1234",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(400);
		
	}
	
	
	@Test
	void creaCliente_cf_duplicato_status409() {
		
		var payload = """
				{
				"codiceFiscale": "RSSMRA80A01H501U",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "aaa@bbb",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(201);
		
		
		given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(409);
		
	}
	
	@Test
	void getAllClienti() {
		given()
		.contentType("application/json")
		.when()
		.get("/clienti/all")
		.then()
		.statusCode(200);
	}
	
	@Test
	void getCliente() {
		
		var payload = """
				{
				"codiceFiscale": "RSSMRA82A01H501U",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "aaa@bbb",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		var id = given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(201).extract().path("id");
		
		given()
		.contentType("application/json")
		.when()
		.get("/clienti/{id}", id)
		.then()
		.statusCode(200)
		.body("id", equalTo(id));
		
	}
	
	@Test
	void delete_then_404() {
		
		var payload = """
				{
				"codiceFiscale": "RSSMRA86A01H501U",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "aaa@bbb",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		var id = given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(201).extract().path("id");
		
		
		given()
		.contentType("application/json")
		.when()
		.delete("/clienti/{id}", id)
		.then()
		.statusCode(204);
		
		given()
		.contentType("application/json")
		.when()
		.get("/clienti/{id}", id)
		.then()
		.statusCode(404);
		
	}
	
	@Test
	void updateCliente() {
		
		
		var payload = """
				{
				"codiceFiscale": "RSSMRA84A01H501U",
				"nome": "Mario",
				"cognome": "Rossi",
				"email": "aaa@bbb",
				"telefono": "1234",
				"indirizzo": "Via Roma 1",
				"citta": "Roma",
				"cap": "00042",
				"paese": "Italia"
				}
				""";
		
		var id = given()
		.contentType("application/json")
		.body(payload)
		.when()
		.post("/clienti/crea")
		.then()
		.statusCode(201).extract().path("id");
		
		payload = String.format("""
				{
				"id": %s,
				"codiceFiscale": "RSSMRA84A01H501U",
				"nome": "Luca",
				"cognome": "Verdi",
				"email": "ccc@ddd",
				"telefono": "3456",
				"indirizzo": "Via Verdi 3",
				"citta": "Latina",
				"cap": "04100",
				"paese": "Italia"
				}
				""", id.toString());
		
		given()
		.contentType("application/json")
		.body(payload)
		.when()
		.put("/clienti/modifica")
		.then()
		.statusCode(200)
		.body("nome", equalTo("Luca"))
		.body("cognome", equalTo("Verdi"))
		.body("email", equalTo("ccc@ddd"))
		.body("telefono", equalTo("3456"))
		.body("indirizzo", equalTo("Via Verdi 3"))
		.body("citta", equalTo("Latina"))
		.body("cap", equalTo("04100"))
		.body("paese", equalTo("Italia"));
		
	}

}
