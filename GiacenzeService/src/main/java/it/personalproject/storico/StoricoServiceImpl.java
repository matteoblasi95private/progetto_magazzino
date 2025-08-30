package it.personalproject.storico;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.giacenze.converters.MagazziniModelToMagazziniEntityConverter;
import it.personalproject.giacenze.converters.ProdottiModelToProdottiEntityConverter;
import it.personalproject.giacenze.converters.StoricoMagazziniEntityToModelConverter;
import it.personalproject.giacenze.domain.MagazzinoModel;
import it.personalproject.giacenze.domain.ProdottiModel;
import it.personalproject.giacenze.domain.StoricoMagazzinoModel;
import it.personalproject.giacenze.entities.TisMagazzinoStoricoMovimenti;
import it.personalproject.giacenze.repositories.StoricoMagazziniRepository;

@Service
public class StoricoServiceImpl implements StoricoService {
	
	@Autowired
	private StoricoMagazziniRepository storicoMagazziniRepository;
	
	@Autowired
	private StoricoMagazziniEntityToModelConverter storicoMagazziniEntityToModelConverter;
	
	@Autowired
	private MagazziniModelToMagazziniEntityConverter magazzinoModelToEntityConverter;
	
	@Autowired
	private ProdottiModelToProdottiEntityConverter prodottiModelToProdottiEntityConverter;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void scriviStoricoMovimenti(MagazzinoModel magazzino, ProdottiModel prodotto, Integer quantitaMovimento,
			String tipoMovimento, String riferimento, String utente, String note) {
		
		TisMagazzinoStoricoMovimenti storicoRecord = new TisMagazzinoStoricoMovimenti();
		
		storicoRecord.setDataMovimento(LocalDateTime.now());
		
		storicoRecord.setMagazzino(magazzinoModelToEntityConverter.convert(magazzino));
		
		storicoRecord.setProdotto(prodottiModelToProdottiEntityConverter.convert(prodotto));
		
		storicoRecord.setQuantitaMovimento(quantitaMovimento);
		
		storicoRecord.setRiferimento(riferimento);
		
		storicoRecord.setTipoMovimento(tipoMovimento);
		
		storicoRecord.setRiferimento(riferimento);
		
		storicoRecord.setRiferimento(riferimento);
		
		storicoRecord.setUtente(utente);
		
		storicoRecord.setNote(note);
		
		storicoMagazziniRepository.save(storicoRecord);
		
	}
	

	@Override
	@Transactional(readOnly = true)
	public Collection<StoricoMagazzinoModel> getStoricoMovimentiProdotto(Integer idProdotto) {
		
		Collection<StoricoMagazzinoModel> storicoMagazzinoModel = new LinkedList<>();
		
		Collection<TisMagazzinoStoricoMovimenti> storicoMovimentiProdotto = storicoMagazziniRepository.getStoricoProdotto(idProdotto);
		
		if(!storicoMovimentiProdotto.isEmpty()) {
			storicoMovimentiProdotto.stream().forEach(s -> storicoMagazzinoModel.add(storicoMagazziniEntityToModelConverter.convert(s)));
		}
		
		return storicoMagazzinoModel;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<StoricoMagazzinoModel> getStoricoMovimentiMagazzino(Integer idMagazzino) {
		
		Collection<StoricoMagazzinoModel> storicoMagazzinoModel = new LinkedList<>();
		
		Collection<TisMagazzinoStoricoMovimenti> storicoMovimentiMagazzino = storicoMagazziniRepository.getStoricoMagazzino(idMagazzino);
		
		if(!storicoMovimentiMagazzino.isEmpty()) {
			storicoMovimentiMagazzino.stream().forEach(s -> storicoMagazzinoModel.add(storicoMagazziniEntityToModelConverter.convert(s)));
		}
		
		return storicoMagazzinoModel;
		
	}

}
