package it.personalproject.spedizioni.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.converters.SpedizioniEntityToOrdiniModelConverter;
import it.personalproject.spedizioni.converters.SpedizioniModelToOrdiniEntityConverter;
import it.personalproject.spedizioni.converters.StoricoSpedizioniEntityToModelConverter;
import it.personalproject.spedizioni.entities.TfStatoCorriere;
import it.personalproject.spedizioni.entities.TisClienti;
import it.personalproject.spedizioni.entities.TisCorrieri;
import it.personalproject.spedizioni.entities.TisSpedizioni;
import it.personalproject.spedizioni.entities.TisSpedizioniStorico;
import it.personalproject.spedizioni.repositories.ClientiRepository;
import it.personalproject.spedizioni.repositories.CorrieriRepository;
import it.personalproject.spedizioni.repositories.SpedizioniRepository;
import it.personalproject.spedizioni.repositories.SpedizioniStoricoRepository;
import it.personalproject.spedizioni.repositories.StatoCorriereRepository;
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
	
	private final ClientiRepository clientiRepository;
	
	private final CorrieriRepository corrieriRepository;
	
	private final StatoCorriereRepository statoCorriereRepository;
	
	@Autowired
	public SpedizioniServiceImpl(SpedizioniRepository spedizioniRepository, SpedizioniStoricoRepository spedizioniStoricoRepository, StatoSpedizioneRepository statoSpedizioneRepository, SpedizioniModelToOrdiniEntityConverter spedizioniModelToOrdiniEntityConverter, SpedizioniEntityToOrdiniModelConverter spedizioniEntityToOrdiniModelConverter, StoricoSpedizioniEntityToModelConverter storicoSpedizioniEntityToModelConverter, ClientiRepository clientiRepository, CorrieriRepository corrieriRepository, StatoCorriereRepository statoCorriereRepository) {
		this.spedizioniRepository = spedizioniRepository;
		this.spedizioniStoricoRepository = spedizioniStoricoRepository;
		this.statoSpedizioneRepository = statoSpedizioneRepository;
		this.spedizioniModelToOrdiniEntityConverter = spedizioniModelToOrdiniEntityConverter;
		this.spedizioniEntityToOrdiniModelConverter = spedizioniEntityToOrdiniModelConverter;
		this.storicoSpedizioniEntityToModelConverter = storicoSpedizioniEntityToModelConverter;
		this.clientiRepository = clientiRepository;
		this.corrieriRepository = corrieriRepository;
		this.statoCorriereRepository = statoCorriereRepository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SpedizioneModel creaSpedizione(SpedizioneModel spedizione) {
		
		TisSpedizioni spedizioniEntity = spedizioniModelToOrdiniEntityConverter.convert(spedizione);
		
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
	public Collection<SpedizioneModel> getAllSpedizioni() {
				
		Collection<TisSpedizioni> spedizioniList = spedizioniRepository.findAll();
		
		if(spedizioniList == null || spedizioniList.isEmpty()) {
			return Collections.emptyList();
		}
		
		return spedizioniList.stream().map(spedizioniEntityToOrdiniModelConverter::convert).collect(Collectors.toList());
		
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
				
		Collection<TisSpedizioniStorico> storicoSpedizioni = spedizioniStoricoRepository.getStoricoSpedizione(id);
		
		if(storicoSpedizioni == null || storicoSpedizioni.isEmpty()) {
			return Collections.emptyList();
		}
		
		return storicoSpedizioni.stream().map(storicoSpedizioniEntityToModelConverter::convert).collect(Collectors.toList());
	}

	@Override
	public SpedizioneModel creaSpedizioneFromOrdine(OrdineModel o) throws CorrieriAttiviLiberiNotFoundException {
		
		TisClienti clienteEntity = clientiRepository.findById(o.getIdCliente()).orElseThrow(() -> new EntityNotFoundException("ERRORE CREAZIONE SPEDIZIONE DA ORDINE " + o.getId() + " - CLIENTE NON TROVATO SUGLI ARCHIVI"));
		
		SpedizioneModel spedizione = new SpedizioneModel();
		
		spedizione.setIdOrdine(o.getId());
		spedizione.setIdCliente(o.getIdCliente());
		spedizione.setTrackingNumber("1");
		spedizione.setDataConsegnaPrevista(LocalDateTime.now().plusDays(1));
		spedizione.setDestCitta(clienteEntity.getCitta());
		spedizione.setDestProvincia("RM");
		spedizione.setDataCreazione(LocalDateTime.now());
		spedizione.setDestIndirizzo(clienteEntity.getIndirizzo());
		spedizione.setDestNome(clienteEntity.getCitta());
		spedizione.setDestCap(clienteEntity.getCap());
		spedizione.setDestPaese(clienteEntity.getPaese());
		spedizione.setIdStato(1);
		spedizione.setCostoSpedizione(BigDecimal.valueOf(30));
		
		Optional<Integer> idCorriereAttivoLibero = getCorriereLibero(spedizione);
		
		if(idCorriereAttivoLibero.isPresent()) {
			spedizione.setIdCorriere(idCorriereAttivoLibero.get());
		}
		
		else {
			throw new CorrieriAttiviLiberiNotFoundException("ERRORE - NESSUN CORRIERE ATTIVO LIBERO TROVATO PER CREAZIONE SPEDIZIONE LEGATA A ORDINE: " + o.getId());
		}
		
		return creaSpedizione(spedizione);
		
	}

	private Optional<Integer> getCorriereLibero(SpedizioneModel spedizione) {
		
		TfStatoCorriere statoAttivo = statoCorriereRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("ERRORE - STATO CORRIERE ATTIVO NON TROVATO NEGLI ARCHIVI"));
		
		Collection<TisCorrieri> corrieriLiberiAttivi = corrieriRepository.findByIdStatoCorriereAndAttivo(statoAttivo, true);
		
		if(corrieriLiberiAttivi.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(corrieriLiberiAttivi.iterator().next().getId());
		
	}

}
