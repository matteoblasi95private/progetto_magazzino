package it.personalproject.prodotti.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.personalproject.prodotti.domain.ProdottiModel;
import it.personalproject.prodotti.domain.ProdottiService;

@RestController
@RequestMapping("/prodotti")
public class ProdottiController {
	
	private final ProdottiService prodottiService;
	
	@Autowired
	public ProdottiController(ProdottiService prodottiService) {
		this.prodottiService = prodottiService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<ProdottiModel> creaProdotto(@RequestBody ProdottiModel prodotto) {
		
        ProdottiModel nuovo = prodottiService.creaProdotto(prodotto);
		
		URI location = URI.create("/prodotti/" + nuovo.getId());
        return ResponseEntity.created(location).body(nuovo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdottiModel> getProdotto(@PathVariable("id") Integer id) {
		
		ProdottiModel prodotto = prodottiService.getProdotto(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(prodotto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity cancellaProdotto(@PathVariable("id") Integer id) {
		
		prodottiService.cancellaProdotto(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<ProdottiModel> aggiornaProdotto(@RequestBody ProdottiModel prodotto) {
		
		ProdottiModel aggiornato = prodottiService.aggiornaProdotto(prodotto);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(aggiornato);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<ProdottiModel>> getAllProdotti() {
		
		List<ProdottiModel> prodotti = prodottiService.getAllProdotti();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(prodotti);
	}

}
