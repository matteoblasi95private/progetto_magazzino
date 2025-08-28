package it.personalproject.spedizioni.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.spedizioni.converters.SpedizioniEntityToOrdiniModelConverter;
import it.personalproject.spedizioni.converters.SpedizioniModelToOrdiniEntityConverter;
import it.personalproject.spedizioni.converters.StoricoSpedizioniEntityToModelConverter;
import it.personalproject.spedizioni.entities.TisSpedizioni;
import it.personalproject.spedizioni.entities.TisSpedizioniStorico;
import it.personalproject.spedizioni.repositories.SpedizioniRepository;
import it.personalproject.spedizioni.repositories.SpedizioniStoricoRepository;
import it.personalproject.spedizioni.repositories.StatoSpedizioneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SpedizioniServiceImpl implements SpedizioniService {
	
	private final SpedizioniRepository spedizioniRepository;
	
	private final SpedizioniStoricoRepository spedizioniStoricoRepository;
	
	private final StatoSpedizioneRepository statoSpedizioneRepository;
	
	private final SpedizioniModelToOrdiniEntityConverter spedizioniModelToOrdiniEntityConverter;
	
	private final SpedizioniEntityToOrdiniModelConverter spedizioniEntityToOrdiniModelConverter;
	
	private final StoricoSpedizioniEntityToModelConverter storicoSpedizioniEntityToModelConverter;
	
	@Autowired
	public SpedizioniServiceImpl(SpedizioniRepository spedizioniRepository, SpedizioniStoricoRepository spedizioniStoricoRepository, StatoSpedizioneRepository statoSpedizioneRepository, SpedizioniModelToOrdiniEntityConverter spedizioniModelToOrdiniEntityConverter, SpedizioniEntityToOrdiniModelConverter spedizioniEntityToOrdiniModelConverter, StoricoSpedizioniEntityToModelConverter storicoSpedizioniEntityToModelConverter) {
		this.spedizioniRepository = spedizioniRepository;
		this.spedizioniStoricoRepository = spedizioniStoricoRepository;
		this.statoSpedizioneRepository = statoSpedizioneRepository;
		this.spedizioniModelToOrdiniEntityConverter = spedizioniModelToOrdiniEntityConverter;
		this.spedizioniEntityToOrdiniModelConverter = spedizioniEntityToOrdiniModelConverter;
		this.storicoSpedizioniEntityToModelConverter = storicoSpedizioniEntityToModelConverter;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SpedizioneModel creaSpedizione(SpedizioneModel ordine) {
		
		TisSpedizioni spedizioniEntity = spedizioniModelToOrdiniEntityConverter.convert(ordine);
		
		spedizioniEntity.setDataCreazione(LocalDateTime.now());
		
		spedizioniEntity.setDataAggiornamento(LocalDateTime.now());
		
		spedizioniEntity = spedizioniRepository.save(spedizioniEntity);
		
		scriviStoricoSpedizione(spedizioniEntity, "CREAZIONE");
		
		return spedizioniEntityToOrdiniModelConverter.convert(spedizioniEntity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public SpedizioneModel getSpedizione(Integer id) {
		
		SpedizioneModel result = null;
	
		Optional<TisSpedizioni> ordineEntity = spedizioniRepository.findById(id);
		
		if(ordineEntity.isPresent()) {
			result = spedizioniEntityToOrdiniModelConverter.convert(ordineEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancellaSpedizione(Integer id) {
		
		TisSpedizioni spedizioneEntity = spedizioniRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SPEDIZIONE NON TROVATA ASSOCIATO A ID " + id));
		
		spedizioneEntity.setStato(statoSpedizioneRepository.findByCodice("ANNULLATA").orElseThrow(() -> new EntityNotFoundException("STATO ASSOCIATO A ANNULLATA NON TROVATO")));
		
		spedizioniRepository.save(spedizioneEntity);
		
		scriviStoricoSpedizione(spedizioneEntity, "ANNULLAMENTO");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SpedizioneModel aggiornaSpedizione(SpedizioneModel spedizione) {
		
		if(spedizione.getId() == null) {
			throw new IllegalArgumentException("ERRORE AGGIORNA ORDINE " + spedizione.getId() + " - ID SPEDIZIONE NON VALORIZZATO");
		}
		
		TisSpedizioni spedizioneEntity = spedizioniRepository.findById(spedizione.getId()).orElseThrow(() -> new EntityNotFoundException("SPEDIZIONE NON TROVATA ASSOCIATO A ID " + spedizione.getId()));
				
		spedizioneEntity.setDataAggiornamento(LocalDateTime.now());
		
		return spedizioniEntityToOrdiniModelConverter.convert(spedizioniRepository.save(spedizioneEntity));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<SpedizioneModel> getAllSpedizioni() {
		
		List<SpedizioneModel> result = new LinkedList<>();
		
		List<TisSpedizioni> spedizioniList = spedizioniRepository.findAll();
		
		if(!spedizioniList.isEmpty()) {
			spedizioniList.stream().forEach(o -> result.add(spedizioniEntityToOrdiniModelConverter.convert(o)));
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void scriviStoricoSpedizione(TisSpedizioni spedizione, String note) {
		
		
		TisSpedizioniStorico storico = new TisSpedizioniStorico(spedizione, spedizione.getStato(), note);
		spedizioniStoricoRepository.save(storico);
		
		
	}

	@Override
	public String getStatoSpedizione(Integer id) {
		
		TisSpedizioni spedizioneEntity = spedizioniRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SPEDIZIONE NON TROVATA ASSOCIATO A ID " + id));

		if(spedizioneEntity.getStato() == null || StringUtils.isBlank(spedizioneEntity.getStato().getCodice())) {
			throw new IllegalStateException("ERRORE METODO getStatoSpedizione [ID-SPEDIZIONE: " + id + "] - STATO ASSOCIATO A SPEDIZIONE NON VALORIZZATO");
		}
		
		return spedizioneEntity.getStato().getCodice();
		
	}

	@Override
	public Collection<StoricoSpedizioniModel> getStoricoSpedizione(Integer id) {
		
		Collection<StoricoSpedizioniModel> listStoricoModel = new LinkedList<>();
		
		Collection<TisSpedizioniStorico> storicoSpedizioni = spedizioniStoricoRepository.getStoricoSpedizione(id);
		
		if(!storicoSpedizioni.isEmpty()) {
			storicoSpedizioni.stream().forEach(s -> listStoricoModel.add(storicoSpedizioniEntityToModelConverter.convert(s)));
		}
		
		return listStoricoModel;
	}

}
