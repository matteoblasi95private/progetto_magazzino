package it.personalproject.spedizioni.controller;

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

import it.personalproject.spedizioni.domain.SpedizioneModel;
import it.personalproject.spedizioni.domain.SpedizioniService;
import it.personalproject.spedizioni.domain.StoricoSpedizioniModel;

@RestController
@RequestMapping("/spedizioni")
public class SpedizioniController {
	
	private final SpedizioniService spedizioniService;
	
	@Autowired
	public SpedizioniController(SpedizioniService spedizioniService) {
		this.spedizioniService = spedizioniService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<SpedizioneModel> creaSpedizione(@RequestBody SpedizioneModel spedizione) {
		
        SpedizioneModel nuovo = spedizioniService.creaSpedizione(spedizione);
		
		URI location = URI.create("/spedizioni/" + nuovo.getId());
        return ResponseEntity.created(location).body(nuovo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SpedizioneModel> getSpedizione(@PathVariable("id") Integer id) {
		
		SpedizioneModel spedizione = spedizioniService.getSpedizione(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(spedizione);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity cancellaSpedizione(@PathVariable("id") Integer id) {
		
		spedizioniService.cancellaSpedizione(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<SpedizioneModel> aggiornaSpedizione(@RequestBody SpedizioneModel ordine) {
		SpedizioneModel aggiornato = spedizioniService.aggiornaSpedizione(ordine);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(aggiornato);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<SpedizioneModel>> getAllSpedizioni() {
		
		List<SpedizioneModel> spedizioni = spedizioniService.getAllSpedizioni();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(spedizioni);
	}
	
	
	@GetMapping("/{id}/stato")
	public ResponseEntity<String> getStatoSpedizione(@PathVariable("id") Integer id) {
		
		String statoCorrente = spedizioniService.getStatoSpedizione(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(statoCorrente);
	}
	
	@GetMapping("/{id}/storico")
	public ResponseEntity<List<StoricoSpedizioniModel>> getStoricoSpedizione(@PathVariable("id") Integer id) {
	
		List<StoricoSpedizioniModel> storico = spedizioniService.getStoricoSpedizione(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(storico);
	}

}
