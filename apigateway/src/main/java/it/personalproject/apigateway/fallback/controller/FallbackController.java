package it.personalproject.apigateway.fallback.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	@GetMapping("/fallback/ordini")
	public Mono<Map<String,String>> fallbackOrdini() {
	    return Mono.just(Map.of("message","Servizio Ordini non disponibile"));
	}
	
	@GetMapping("/fallback/prodotti")
	public Mono<Map<String,String>> fallbackProdotti() {
	    return Mono.just(Map.of("message","Servizio Prodotti non disponibile"));
	}
	
	@GetMapping("/fallback/clienti")
	public Mono<Map<String,String>> fallbackClienti() {
	    return Mono.just(Map.of("message","Servizio Clienti non disponibile"));
	}
	
	@GetMapping("/fallback/auth")
	public Mono<Map<String,String>> fallbackAuth() {
	    return Mono.just(Map.of("message","Servizio Auth non disponibile"));
	}

}
