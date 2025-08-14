package it.personalproject.ordini.controller;

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

import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.domain.OrdiniService;

@RestController
@RequestMapping("/ordini")
public class OrdiniController {
	
	private final OrdiniService ordiniService;
	
	@Autowired
	public OrdiniController(OrdiniService ordiniService) {
		this.ordiniService = ordiniService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<OrdineModel> creaOrdine(@RequestBody OrdineModel ordine) {
		
        OrdineModel nuovo = ordiniService.creaOrdine(ordine);
		
		URI location = URI.create("/ordini/" + nuovo.getId());
        return ResponseEntity.created(location).body(nuovo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdineModel> getOrdine(@PathVariable("id") Integer id) {
		
		OrdineModel ordine = ordiniService.getOrdine(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(ordine);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity cancellaOrdine(@PathVariable("id") Integer id) {
		
		ordiniService.cancellaOrdine(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<OrdineModel> aggiornaOrdine(@RequestBody OrdineModel ordine) {
		OrdineModel aggiornato = ordiniService.aggiornaOrdine(ordine);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(aggiornato);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<OrdineModel>> getAllOrdini() {
		
		List<OrdineModel> ordini = ordiniService.getAllOrdini();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(ordini);
	}

}
