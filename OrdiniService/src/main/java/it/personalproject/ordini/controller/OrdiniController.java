package it.personalproject.ordini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.domain.OrdiniService;

@RestController
public class OrdiniController {
	
	private final OrdiniService ordiniService;
	
	@Autowired
	public OrdiniController(OrdiniService ordiniService) {
		this.ordiniService = ordiniService;
	}
	
	@PostMapping("/ordini/crea")
	public void creaOrdine(@RequestBody OrdineModel ordine) {
		ordiniService.creaOrdine(ordine);
	}
	
	@GetMapping("/ordini/{id}")
	public OrdineModel getOrdine(@PathVariable("id") Integer id) {
		return ordiniService.getOrdine(id);	
	}
	
	@DeleteMapping("/ordini/{id}")
	public void cancellaOrdine(@PathVariable("id") Integer id) {
		ordiniService.cancellaOrdine(id);
	}
	
	@PutMapping("/ordini/modifica")
	public void aggiornaOrdine(@RequestBody OrdineModel ordine) {
		ordiniService.aggiornaOrdine(ordine);
	}

}
