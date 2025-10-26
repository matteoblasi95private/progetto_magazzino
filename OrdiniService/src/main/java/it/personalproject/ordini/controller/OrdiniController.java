package it.personalproject.ordini.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.personalproject.ordini.domain.CreaOrdineResponse;
import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.domain.OrdiniService;
import it.personalproject.ordini.exception.OrdineNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordini")
public class OrdiniController {
	
	private final OrdiniService ordiniService;
	
	@Autowired
	public OrdiniController(OrdiniService ordiniService) {
		this.ordiniService = ordiniService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<CreaOrdineResponse> creaOrdine(@Valid @RequestBody OrdineModel ordine) {
		
        CreaOrdineResponse nuovo = ordiniService.creaOrdine(ordine);
		
		URI location = URI.create("/ordini/" + nuovo.ordine().getId());
        return ResponseEntity.created(location).body(nuovo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdineModel> getOrdine(@PathVariable("id") Integer id) {
		
		Optional<OrdineModel> ordine = ordiniService.getOrdine(id);
		
		return ResponseEntity.of(ordine);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> cancellaOrdine(@PathVariable("id") Integer id) {
		
		boolean cancellato = ordiniService.cancellaOrdine(id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cancellato);
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<OrdineModel> aggiornaOrdine(@Valid @RequestBody OrdineModel ordine) throws OrdineNotFoundException {
		OrdineModel aggiornato = ordiniService.aggiornaOrdine(ordine);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(aggiornato);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<Collection<OrdineModel>> getAllOrdini() {
		
		Collection<OrdineModel> ordini = ordiniService.getAllOrdini();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(ordini);
	}

}
