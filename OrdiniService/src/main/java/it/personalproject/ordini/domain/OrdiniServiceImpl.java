package it.personalproject.ordini.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.ordini.converters.OrdiniModelToOrdiniEntityConverter;
import it.personalproject.ordini.domain.events.OrdineCancellatoEvent;
import it.personalproject.ordini.domain.events.OrdineCreatedEvent;
import it.personalproject.ordini.domain.events.OrdineEventHandler;
import it.personalproject.ordini.domain.ports.GiacenzeOutboundPort;
import it.personalproject.ordini.domain.ports.OrdiniEventPublisherPort;
import it.personalproject.ordini.converters.OrdiniEntityToOrdiniModelConverter;
import it.personalproject.ordini.entities.TisClienti;
import it.personalproject.ordini.entities.TisOrdini;
import it.personalproject.ordini.entities.TisProdotti;
import it.personalproject.ordini.exception.OrdineNotFoundException;
import it.personalproject.ordini.repositories.ClientiRepository;
import it.personalproject.ordini.repositories.OrdiniRepository;
import it.personalproject.ordini.repositories.ProdottiRepository;
import it.personalproject.ordini.repositories.StatoOrdineRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdiniServiceImpl implements OrdiniService {
	
	private final OrdiniRepository ordiniRepository;
	
	private final StatoOrdineRepository statoOrdineRepository;
	
	private final ClientiRepository clientiRepository;
	
	private final ProdottiRepository prodottiRepository;
	
	private final OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConverter;
	
	private final OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConverter;
	
	private final GiacenzeOutboundPort giacenzePort;
	
	private final ApplicationEventPublisher events;
	
	@Autowired
	public OrdiniServiceImpl(OrdiniRepository ordiniRepo, StatoOrdineRepository statoOrdineRepository, ClientiRepository clientiRepository, ProdottiRepository prodottiRepository, OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConv, OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConv, GiacenzeOutboundPort giacenzePort, ApplicationEventPublisher events) {
		this.ordiniRepository = ordiniRepo;
		this.statoOrdineRepository = statoOrdineRepository;
		this.clientiRepository = clientiRepository;
		this.prodottiRepository = prodottiRepository;
		this.ordiniModelToOrdiniEntityConverter = ordiniModelToOrdiniEntityConv;
		this.ordiniEntityToOrdiniModelConverter = ordiniEntityToOrdiniModelConv;
		this.giacenzePort = giacenzePort;
		this.events = events;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreaOrdineResponse creaOrdine(OrdineModel ordine) {
		
		CreaOrdineResponse creaOrdineResponse;
		
		Collection<MagazzinoModel> magazziniDispOrdine = giacenzePort.getMagazziniConDisponibilitaProdotto(ordine.getIdProdotto(), ordine.getQuantitaOrdinata());
		
		if(!magazziniDispOrdine.isEmpty()) {
			
			TisOrdini ordineEntity = ordiniModelToOrdiniEntityConverter.convert(ordine);
			
			ordineEntity.setDataCreazione(LocalDateTime.now());
			
			ordineEntity.setDataAggiornamento(LocalDateTime.now());
			
			ordineEntity.setIdStatoOrdine(statoOrdineRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("STATO ORDINE CREATO (ID 1) NON TROVATO")));
			
			ordineEntity = ordiniRepository.save(ordineEntity);
			
			ordine = ordiniEntityToOrdiniModelConverter.convert(ordineEntity);
			
			creaOrdineResponse = new CreaOrdineResponse(ordine, true);
			
			OrdineCreatedEvent ordineCreazioneEvent = new OrdineCreatedEvent(ordine, magazziniDispOrdine.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("ERRORE CREAZIONE ORDINE - MAGAZZINO NON TROVATO")));
			
			events.publishEvent(ordineCreazioneEvent);
		
		}
		else {
			creaOrdineResponse = new CreaOrdineResponse(ordine, false);
		}
		
		return creaOrdineResponse;
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<OrdineModel> getOrdine(Integer id) {
			
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		
		if(ordineEntity.isPresent()) {
			return Optional.of(ordiniEntityToOrdiniModelConverter.convert(ordineEntity.get()));
		}
		
		else {
			return Optional.empty();
		}
				
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean cancellaOrdine(Integer id) {
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		if(ordineEntity.isPresent() && !ordineEntity.get().getIdStatoOrdine().getId().equals(2) && !ordineEntity.get().getIdStatoOrdine().getId().equals(5)) {
			var ordineModel = ordiniEntityToOrdiniModelConverter.convert(ordineEntity.get());
			ordineEntity.get().setIdStatoOrdine(statoOrdineRepository.findById(2).orElseThrow(() -> new EntityNotFoundException("STATO ORDINE CANCELLATO (ID 2) NON TROVATO")));
			events.publishEvent(new OrdineCancellatoEvent(ordineModel));
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrdineModel aggiornaOrdine(OrdineModel ordine) throws OrdineNotFoundException {
		
		Optional<TisOrdini> optionalOrdine = ordiniRepository.findById(ordine.getId());
		
		if(optionalOrdine.isPresent()) {
			
			TisOrdini ordineEntity = optionalOrdine.get();
			
			Optional<TisClienti> cliente = clientiRepository.findById(ordine.getIdCliente());
			
			if(!cliente.isPresent()) {
				throw new EntityNotFoundException("CLIENTE NON TROVATO ASSOCIATO A ORDINE " + ordine.getId());
			}
			
			
			Optional<TisProdotti> prodotto = prodottiRepository.findById(ordine.getIdProdotto());

			if(!prodotto.isPresent()) {
				throw new EntityNotFoundException("PRODOTTO NON TROVATO ASSOCIATO A ORDINE " + ordine.getId());
			}
			
			ordineEntity.setIdCliente(cliente.get());
			
			ordineEntity.setIdProdotto(prodotto.get());
			
			ordineEntity.setDataAggiornamento(LocalDateTime.now());
			
			return ordiniEntityToOrdiniModelConverter.convert(ordiniRepository.save(ordineEntity));
			
		}
		
		else {
			throw new OrdineNotFoundException("ORDINE NON TROVATO ASSOCIATO A ID " + ordine.getId());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<OrdineModel> getAllOrdini() {
		
		List<TisOrdini> ordiniList = ordiniRepository.findAll();
		
		if(ordiniList.isEmpty()) {
			return Collections.emptyList();
		}
		
		return ordiniList.stream()
				.map(ordiniEntityToOrdiniModelConverter::convert).collect(Collectors.toList());
				
	}

}
