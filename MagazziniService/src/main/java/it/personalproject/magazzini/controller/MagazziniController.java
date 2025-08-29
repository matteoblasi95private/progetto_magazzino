package it.personalproject.magazzini.controller;

import java.net.URI;
import java.util.Collection;
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

import it.personalproject.magazzini.domain.MagazzinoModel;
import it.personalproject.magazzini.domain.MagazzinoService;

@RestController
@RequestMapping("/magazzini")
public class MagazziniController {
	
	private final MagazzinoService magazzinoService;
	
	@Autowired
	public MagazziniController(MagazzinoService magazzinoService) {
		this.magazzinoService = magazzinoService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<MagazzinoModel> creaMagazzino(@RequestBody MagazzinoModel spedizione) {
		
        MagazzinoModel nuovo = magazzinoService.creaMagazzino(spedizione);
		
		URI location = URI.create("/magazzini/" + nuovo.getId());
        return ResponseEntity.created(location).body(nuovo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MagazzinoModel> getMagazzino(@PathVariable("id") Integer id) {
		
		MagazzinoModel spedizione = magazzinoService.getMagazzino(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(spedizione);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity cancellaMagazzino(@PathVariable("id") Integer id) {
		
		magazzinoService.cancellaMagazzino(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<MagazzinoModel> aggiornaMagazzino(@RequestBody MagazzinoModel ordine) {
		
		MagazzinoModel aggiornato = magazzinoService.aggiornaMagazzino(ordine);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(aggiornato);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<MagazzinoModel>> getAllMagazzini() {
		
		List<MagazzinoModel> magazzini = magazzinoService.getAllMagazzini();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(magazzini);
	}
	
	
	@GetMapping("/attivi")
	public ResponseEntity<Collection<MagazzinoModel>> getMagazziniAttivi() {
		
		Collection<MagazzinoModel> magazziniAttivi = magazzinoService.getMagazziniAttivi();
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(magazziniAttivi);
	}
	
	
	@GetMapping("/citta/{citta}")
	public ResponseEntity<Collection<MagazzinoModel>> getMagazziniCitta(@PathVariable("citta") String citta) {
		
		Collection<MagazzinoModel> magazziniCitta = magazzinoService.getMagazziniCitta(citta);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(magazziniCitta);
		
	}
	
	
	@GetMapping("/citta/{paese}")
	public ResponseEntity<Collection<MagazzinoModel>> getMagazziniPaese(@PathVariable("paese") String paese) {
		
		Collection<MagazzinoModel> magazziniPaese = magazzinoService.getMagazziniPaese(paese);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(magazziniPaese);
		
	}

}
