package it.personalproject.giacenze.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.giacenze.converters.GiacenzaEntityToModelConverter;
import it.personalproject.giacenze.converters.MagazziniEntityToMagazziniModelConverter;
import it.personalproject.giacenze.converters.StoricoMagazziniEntityToModelConverter;
import it.personalproject.giacenze.entities.TisGiacenze;
import it.personalproject.giacenze.entities.TisGiacenzePK;
import it.personalproject.giacenze.entities.TisMagazzini;
import it.personalproject.giacenze.entities.TisMagazzinoStoricoMovimenti;
import it.personalproject.giacenze.entities.TisProdotti;
import it.personalproject.giacenze.repositories.GiacenzeRepository;
import it.personalproject.giacenze.repositories.MagazziniRepository;
import it.personalproject.giacenze.repositories.ProdottiRepository;
import it.personalproject.giacenze.repositories.StoricoMagazziniRepository;
import it.personalproject.storico.StoricoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GiacenzeServiceImpl implements GiacenzeService {
	
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
		
		giacenzaEntity.setMagazzino(optionalMagazzino.get());
		
		giacenzaEntity.setProdotto(optionalProdotto.get());
		
		giacenzaEntity.setQuantitaDisponibile(giacenza.getQuantitaDisponibile());
		
		giacenzaEntity.setDataAggiornamento(LocalDateTime.now());
				
		GiacenzeModel creata = giacenzaEntityToModelConverter.convert(giacenzeRepository.save(giacenzaEntity));
				
		return creata;
	}

	@Override
	@Transactional(readOnly = true)
	public GiacenzeModel getDettaglioStock(Integer idProdotto, Integer idMagazzino) {
		
		GiacenzeModel result = null;
		
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(idMagazzino, idProdotto);
		
		Optional<TisGiacenze> giacenzeEntity = giacenzeRepository.findById(giacenzaPK);
		
		if(giacenzeEntity.isPresent()) {
			result = giacenzaEntityToModelConverter.convert(giacenzeEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GiacenzeModel aggiornaQuantita(GiacenzeModel giacenza) {
		
		if(giacenza.getQuantitaDisponibile().compareTo(0) <= 0) {
			throw new IllegalArgumentException("ERRORE AGGIORNAMENTO STOCK " + giacenza + " - QUANTITA INFERIORE O UGUALE A ZERO");
		}
		
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(giacenza.getMagazzino().getId(), giacenza.getProdotto().getId());
		
		TisGiacenze giacenzaEntity = giacenzeRepository.findById(giacenzaPK).orElseThrow(() -> new EntityNotFoundException("ERRORE METODO AGGIORNA QUANITTA - GIACENZA NON TROVATA ASSOCIATA A CHIAVE: " + giacenzaPK));

		giacenzaEntity.setQuantitaDisponibile(giacenza.getQuantitaDisponibile());
		
		GiacenzeModel aggiornata = giacenzaEntityToModelConverter.convert(giacenzeRepository.save(giacenzaEntity));
		
		storicoService.scriviStoricoMovimenti(aggiornata.getMagazzino(), aggiornata.getProdotto(), aggiornata.getQuantitaDisponibile(), "AGGIORNAMENTO_QUANTITA", null, null, null);
		
		return aggiornata;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GiacenzeModel> getListaStockProdotto(Integer idProdotto) {
		
		Collection<GiacenzeModel> listaGiacenzeProdotto = new LinkedList<>();
		
		Collection<TisGiacenze> listaStocksEntitiesProdotto = giacenzeRepository.getListaStockProdotto(idProdotto);
		
		if(!listaStocksEntitiesProdotto.isEmpty()) {
			listaStocksEntitiesProdotto.stream().forEach(g -> listaGiacenzeProdotto.add(giacenzaEntityToModelConverter.convert(g)));
		}
		
		return listaGiacenzeProdotto;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GiacenzeModel> getListaStockMagazzino(Integer idMagazzino) {
		
		Collection<GiacenzeModel> listaGiacenzeMagazzino = new LinkedList<>();
		
		Collection<TisGiacenze> listaStocksEntitiesMagazzino = giacenzeRepository.getListaStockMagazzino(idMagazzino);
		
		if(!listaStocksEntitiesMagazzino.isEmpty()) {
			listaStocksEntitiesMagazzino.stream().forEach(g -> listaGiacenzeMagazzino.add(giacenzaEntityToModelConverter.convert(g)));
		}
		
		return listaGiacenzeMagazzino;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita) {
		
		Collection<MagazzinoModel> listaMagazziniDisponibilita = new LinkedList<>();
		
		Collection<TisMagazzini> listaMagazziniEntitiesDisp = giacenzeRepository.getListaMagazziniDisponibilitaProdotto(idProdotto);
		
		if(!listaMagazziniEntitiesDisp.isEmpty()) {
			listaMagazziniEntitiesDisp.stream().forEach(m -> listaMagazziniDisponibilita.add(magazziniEntityToModelConverter.convert(m)));
		}
		
		return listaMagazziniDisponibilita;
		
	}

	@Override
	public GiacenzeModel trasferisciProdotto(TrasferimentoProdottoDTO trasferimentoDTO) {
		
		TisGiacenzePK giacenzaAttualePK = new TisGiacenzePK(trasferimentoDTO.getIdMagazzinoPrecedente(), trasferimentoDTO.getIdProdotto());
		
		TisGiacenze giacenzaAttuale = giacenzeRepository.findById(giacenzaAttualePK).orElseThrow(() -> new EntityNotFoundException("GIACENZA NON TROVATA PER TRASFERIMENTO " + trasferimentoDTO));
		
		if(giacenzaAttuale.getQuantitaDisponibile().compareTo(trasferimentoDTO.getQuantitaTrasferita()) < 0) {
			throw new IllegalStateException("ERRORE METODO trasferisciProdotto per trasferimento " + trasferimentoDTO + " - QUANTITA DISPONIBILE MINORE DI QUELLA TRAASFERITA");
		}
		
		
		
		GiacenzeModel giacenzaNuova = new GiacenzeModel();
		ProdottiModel prodotto = new ProdottiModel();
		prodotto.setId(trasferimentoDTO.getIdProdotto());
		MagazzinoModel magazzinoNuovo = new MagazzinoModel();
		magazzinoNuovo.setId(trasferimentoDTO.getIdMagazzinoNuovo());
		giacenzaNuova.setMagazzino(magazzinoNuovo);
		giacenzaNuova.setProdotto(prodotto);
		giacenzaNuova.setQuantitaDisponibile(trasferimentoDTO.getQuantitaTrasferita());
		
		
		giacenzaAttuale.setQuantitaDisponibile(giacenzaAttuale.getQuantitaDisponibile()-trasferimentoDTO.getQuantitaTrasferita());
		
		if(giacenzaAttuale.getQuantitaDisponibile().equals(0)) {
			cancellaGiacenza(giacenzaNuova);
		}
		
		giacenzeRepository.save(giacenzaAttuale);
		
		return creaStock(giacenzaNuova);
		
	}

	@Override
	public void cancellaGiacenza(GiacenzeModel giacenza) {
		
		TisGiacenzePK giacenzaPK = new TisGiacenzePK(giacenza.getMagazzino().getId(), giacenza.getProdotto().getId());
		
		TisGiacenze giacenzaEntity = giacenzeRepository.findById(giacenzaPK).orElseThrow(() -> new EntityNotFoundException("GIACENZA NON TROVATA PER CANCELLAZIONE"));

		giacenzeRepository.delete(giacenzaEntity);
		
	}

}
