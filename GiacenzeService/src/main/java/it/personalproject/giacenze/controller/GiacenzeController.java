package it.personalproject.giacenze.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.personalproject.giacenze.domain.GiacenzeModel;
import it.personalproject.giacenze.domain.GiacenzeService;
import it.personalproject.giacenze.domain.MagazzinoModel;
import it.personalproject.giacenze.domain.StoricoMagazzinoModel;
import it.personalproject.giacenze.domain.TrasferimentoProdottoDTO;
import it.personalproject.storico.StoricoService;
import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/giacenze")
public class GiacenzeController {
	
	private final GiacenzeService giacenzeService;
	
	private final StoricoService storicoService;
	
	@Autowired
	public GiacenzeController(GiacenzeService giacenzeService, StoricoService storicoService) {
		this.giacenzeService = giacenzeService;
		this.storicoService = storicoService;
	}
	
	@PostMapping("/crea")
	public ResponseEntity<GiacenzeModel> creaStock(@RequestBody GiacenzeModel giacenza) {
		
		GiacenzeModel nuovo = giacenzeService.creaStock(giacenza);
		
        return ResponseEntity.status(HttpStatus.OK).body(nuovo);
		
	}
	
	@GetMapping("/dettaglio")
	public ResponseEntity<GiacenzeModel> getDettaglioStock(@QueryParam("idprodotto") Integer idProdotto, @QueryParam("idmagazzino") Integer idMagazzino) {
		
		GiacenzeModel stock = giacenzeService.getDettaglioStock(idProdotto, idMagazzino);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(stock);
	}
	
	@PutMapping("/modifica")
	public ResponseEntity<GiacenzeModel> aggiornaQuantita(@RequestBody GiacenzeModel giacenza) {
		
		GiacenzeModel stockAggiornato = giacenzeService.aggiornaQuantita(giacenza);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(stockAggiornato);
	}
	
	
	@GetMapping("/prodotto/{id}")
	public ResponseEntity<Collection<GiacenzeModel>> getListaStockProdotto(@PathVariable("id") Integer id) {
		
		Collection<GiacenzeModel> listaStockProdotto = giacenzeService.getListaStockProdotto(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(listaStockProdotto);
	}
	
	@GetMapping("/magazzino/{id}")
	public ResponseEntity<Collection<GiacenzeModel>> getListaStockMagazzino(@PathVariable("id") Integer id) {
		
		Collection<GiacenzeModel> listaStockMagazzino = giacenzeService.getListaStockMagazzino(id);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(listaStockMagazzino);
	}
	
	@GetMapping("/disponibilita")
	public ResponseEntity<Collection<MagazzinoModel>> getMagazziniConDisponibilitaProdotto(@QueryParam("idprodotto") Integer idProdotto, @QueryParam("quantita") Integer quantita) {
	
		Collection<MagazzinoModel> magazziniDispProdotto = giacenzeService.getMagazziniConDisponibilitaProdotto(idProdotto, quantita);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(magazziniDispProdotto);
		
	}
	
	@PostMapping("/trasferimento")
	public ResponseEntity<GiacenzeModel> trasferisciProdotto(@RequestBody TrasferimentoProdottoDTO trasferimentoDTO) {
		
		
		GiacenzeModel giacenzaAggiornata = giacenzeService.trasferisciProdotto(trasferimentoDTO);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(giacenzaAggiornata);
	}
	
	
	@PostMapping("/cancella")
	public ResponseEntity<GiacenzeModel> cancellaGiacenza(@RequestBody GiacenzeModel giacenza) {
		
		
		giacenzeService.cancellaGiacenza(giacenza);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/storico/{idprodotto}")
	public ResponseEntity<Collection<StoricoMagazzinoModel>> getStoricoProdotto(@PathVariable("idprodotto") Integer idProdotto) {
	
		Collection<StoricoMagazzinoModel> storicoProdotto = storicoService.getStoricoMovimentiProdotto(idProdotto);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(storicoProdotto);
		
	}
	
	@GetMapping("/storico/{idmagazzino}")
	public ResponseEntity<Collection<StoricoMagazzinoModel>> getStoricoMagazzino(@PathVariable("idmagazzino") Integer idMagazzino) {
	
		Collection<StoricoMagazzinoModel> storicoMagazzino = storicoService.getStoricoMovimentiMagazzino(idMagazzino);
		
		return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(storicoMagazzino);
		
	}

}
