package it.personalproject.giacenze.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.giacenze.controller.ExceptionController;
import it.personalproject.giacenze.converters.GiacenzaEntityToModelConverter;
import it.personalproject.giacenze.converters.MagazziniEntityToMagazziniModelConverter;
import it.personalproject.giacenze.converters.StoricoMagazziniEntityToModelConverter;
import it.personalproject.giacenze.entities.TisGiacenze;
import it.personalproject.giacenze.entities.TisGiacenzePK;
import it.personalproject.giacenze.entities.TisMagazzini;
import it.personalproject.giacenze.entities.TisMagazzinoStoricoMovimenti;
import it.personalproject.giacenze.entities.TisProdotti;
import it.personalproject.giacenze.exceptions.StockNotFoundException;
import it.personalproject.giacenze.repositories.GiacenzeRepository;
import it.personalproject.giacenze.repositories.MagazziniRepository;
import it.personalproject.giacenze.repositories.ProdottiRepository;
import it.personalproject.giacenze.repositories.StoricoMagazziniRepository;
import it.personalproject.storico.StoricoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GiacenzeServiceImpl implements GiacenzeService {
	
	private static final Logger log = LoggerFactory.getLogger(GiacenzeServiceImpl.class);
	
	@Autowired
	private StoricoService storicoService;
	
	@Autowired
	private GiacenzeRepository giacenzeRepository;
	
	@Autowired
	private MagazziniRepository magazziniRepository;
	
	@Autowired
	private ProdottiRepository prodottiRepository;
	
	@Autowired
	private GiacenzaEntityToModelConverter giacenzaEntityToModelConverter;
	
	@Autowired
	private MagazziniEntityToMagazziniModelConverter magazziniEntityToModelConverter;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GiacenzeModel creaStock(GiacenzeModel giacenza) {
		
		if(giacenza.getMagazzino() == null || giacenza.getProdotto() == null) {
			throw new IllegalArgumentException("ERRORE CREAZIONE STOCK - MAGAZZINO O PRODOTTO NON VALORIZZATI");
		}
		
		if(giacenza.getQuantitaDisponibile().compareTo(0) <= 0) {
			throw new IllegalArgumentException("ERRORE CREAZIONE STOCK - QUANTITA INFERIORE O UGUALE A ZERO");
		}
		
		TisGiacenzePK giacenzePK = new TisGiacenzePK(giacenza.getMagazzino().getId(), giacenza.getProdotto().getId());
		
		Optional<TisGiacenze> optionalGiacenze = giacenzeRepository.findById(giacenzePK);
		
		if(optionalGiacenze.isPresent()) {
			throw new IllegalStateException("ERRORE - GIACENZA GIA ESISTENTE: " + giacenza);
		}
		
		Optional<TisProdotti> optionalProdotto = prodottiRepository.findById(giacenza.getProdotto().getId());
		
		if(!optionalProdotto.isPresent()) {
			throw new EntityNotFoundException("ERRORE - PRODOTTO NON ESISTENTE: " + giacenza.getProdotto().getId());
		}
		
		Optional<TisMagazzini> optionalMagazzino = magazziniRepository.findById(giacenza.getMagazzino().getId());
		
		if(!optionalMagazzino.isPresent()) {
			throw new EntityNotFoundException("ERRORE - PRODOTTO NON ESISTENTE: " + giacenza.getMagazzino().getId());
		}
		
		TisGiacenze giacenzaEntity = new TisGiacenze();
		
		giacenzaEntity.setGiacenzePK(new TisGiacenzePK());
						
		giacenzaEntity.setMagazzino(optionalMagazzino.get());
		
		giacenzaEntity.setProdotto(optionalProdotto.get());
		
		giacenzaEntity.setQuantitaDisponibile(giacenza.getQuantitaDisponibile());
		
		giacenzaEntity.setDataAggiornamento(LocalDateTime.now());
				
		GiacenzeModel creata = giacenzaEntityToModelConverter.convert(giacenzeRepository.save(giacenzaEntity));
				
		return creata;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<GiacenzeModel> getDettaglioStock(Integer idProdotto, Integer idMagazzino) {
				
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(idMagazzino, idProdotto);
		
		Optional<TisGiacenze> giacenzeEntity = giacenzeRepository.findById(giacenzaPK);
		
		if(giacenzeEntity.isPresent()) {
			return Optional.of(giacenzaEntityToModelConverter.convert(giacenzeEntity.get()));
		}
		else {
			return Optional.empty();
		}
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GiacenzeModel aggiornaQuantita(GiacenzeModel giacenza) throws StockNotFoundException {
		
		if(giacenza.getQuantitaDisponibile().compareTo(0) <= 0) {
			throw new IllegalArgumentException("ERRORE AGGIORNAMENTO STOCK " + giacenza + " - QUANTITA INFERIORE O UGUALE A ZERO");
		}
		
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(giacenza.getMagazzino().getId(), giacenza.getProdotto().getId());
		
		TisGiacenze giacenzaEntity = giacenzeRepository.findById(giacenzaPK).orElseThrow(() -> new StockNotFoundException("ERRORE METODO AGGIORNA QUANITTA - GIACENZA NON TROVATA ASSOCIATA A CHIAVE: " + giacenzaPK));

		giacenzaEntity.setQuantitaDisponibile(giacenza.getQuantitaDisponibile());
		
		GiacenzeModel aggiornata = giacenzaEntityToModelConverter.convert(giacenzeRepository.save(giacenzaEntity));
		
		storicoService.scriviStoricoMovimenti(aggiornata.getMagazzino(), aggiornata.getProdotto(), aggiornata.getQuantitaDisponibile(), "AGGIORNAMENTO_QUANTITA", null, null, null);
		
		return aggiornata;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GiacenzeModel> getListaStockProdotto(Integer idProdotto) {
		
		
		Collection<TisGiacenze> listaStocksEntitiesProdotto = giacenzeRepository.getListaStockProdotto(idProdotto);
		
		if(listaStocksEntitiesProdotto.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listaStocksEntitiesProdotto.stream()
		.map(giacenzaEntityToModelConverter::convert).collect(Collectors.toList());
				
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GiacenzeModel> getListaStockMagazzino(Integer idMagazzino) {
				
		Collection<TisGiacenze> listaStocksEntitiesMagazzino = giacenzeRepository.getListaStockMagazzino(idMagazzino);
		
		if (listaStocksEntitiesMagazzino == null || listaStocksEntitiesMagazzino.isEmpty()) {
	        return Collections.emptyList();
	    }
		
		return listaStocksEntitiesMagazzino.stream()
				.map(giacenzaEntityToModelConverter::convert).collect(Collectors.toList());
				
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita) {
				
		Collection<TisMagazzini> listaMagazziniEntitiesDisp = giacenzeRepository.getListaMagazziniDisponibilitaProdotto(idProdotto, quantita);
		
		if(listaMagazziniEntitiesDisp == null || listaMagazziniEntitiesDisp.isEmpty()) {
			return Collections.emptyList();
		}
		
		log.info("GIACENZE SERVICE - RICHIESTA DISPONIBILITA PRODOTTO: {}, quantita: {}, TROVATA DISPONIBILITA MAGAZZINI: {}", idProdotto, quantita, listaMagazziniEntitiesDisp);
		
		return listaMagazziniEntitiesDisp.stream()
				.map(magazziniEntityToModelConverter::convert).collect(Collectors.toList());
		
	}

	@Override
	public GiacenzeModel trasferisciProdotto(TrasferimentoProdottoDTO trasferimentoDTO) throws StockNotFoundException {
		
		TisGiacenzePK giacenzaAttualePK = new TisGiacenzePK(trasferimentoDTO.idMagazzinoPrecedente(), trasferimentoDTO.idProdotto());
		
		TisGiacenze giacenzaAttuale = giacenzeRepository.findById(giacenzaAttualePK).orElseThrow(() -> new StockNotFoundException("GIACENZA NON TROVATA PER TRASFERIMENTO " + trasferimentoDTO));
		
		if(giacenzaAttuale.getQuantitaDisponibile().compareTo(trasferimentoDTO.quantitaTrasferita()) < 0) {
			throw new IllegalStateException("ERRORE METODO trasferisciProdotto per trasferimento " + trasferimentoDTO + " - QUANTITA DISPONIBILE MINORE DI QUELLA TRAASFERITA");
		}
		
		
		
		GiacenzeModel giacenzaNuova = new GiacenzeModel();
		ProdottiModel prodotto = new ProdottiModel();
		prodotto.setId(trasferimentoDTO.idProdotto());
		MagazzinoModel magazzinoNuovo = new MagazzinoModel();
		magazzinoNuovo.setId(trasferimentoDTO.idMagazzinoNuovo());
		giacenzaNuova.setMagazzino(magazzinoNuovo);
		giacenzaNuova.setProdotto(prodotto);
		giacenzaNuova.setQuantitaDisponibile(trasferimentoDTO.quantitaTrasferita());
		
		
		giacenzaAttuale.setQuantitaDisponibile(giacenzaAttuale.getQuantitaDisponibile()-trasferimentoDTO.quantitaTrasferita());
		
		if(giacenzaAttuale.getQuantitaDisponibile().equals(0)) {
			cancellaGiacenza(giacenzaNuova);
		}
		
		giacenzeRepository.save(giacenzaAttuale);
		
		return creaStock(giacenzaNuova);
		
	}

	@Override
	public void cancellaGiacenza(GiacenzeModel giacenza) throws StockNotFoundException {
		
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(giacenza.getMagazzino().getId(), giacenza.getProdotto().getId());
		
		TisGiacenze giacenzaEntity = giacenzeRepository.findById(giacenzaPK).orElseThrow(() -> new StockNotFoundException("GIACENZA NON TROVATA PER CANCELLAZIONE"));

		giacenzeRepository.delete(giacenzaEntity);
		
	}

}
