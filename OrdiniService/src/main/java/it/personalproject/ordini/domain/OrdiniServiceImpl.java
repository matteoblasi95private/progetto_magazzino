package it.personalproject.ordini.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.ordini.converters.OrdiniModelToOrdiniEntityConverter;
import it.personalproject.ordini.domain.events.OrdineCreatedEvent;
import it.personalproject.ordini.domain.ports.GiacenzeOutboundPort;
import it.personalproject.ordini.domain.ports.OrdiniEventPublisherPort;
import it.personalproject.ordini.converters.OrdiniEntityToOrdiniModelConverter;
import it.personalproject.ordini.entities.TisClienti;
import it.personalproject.ordini.entities.TisOrdini;
import it.personalproject.ordini.entities.TisProdotti;
import it.personalproject.ordini.repositories.ClientiRepository;
import it.personalproject.ordini.repositories.OrdiniRepository;
import it.personalproject.ordini.repositories.ProdottiRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdiniServiceImpl implements OrdiniService {
	
	private final OrdiniRepository ordiniRepository;
	
	private final ClientiRepository clientiRepository;
	
	private final ProdottiRepository prodottiRepository;
	
	private final OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConverter;
	
	private final OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConverter;
	
	private final GiacenzeOutboundPort giacenzePort;
	
	private final OrdiniEventPublisherPort ordiniEventPublisher;
	
	@Autowired
	public OrdiniServiceImpl(OrdiniRepository ordiniRepo, ClientiRepository clientiRepository, ProdottiRepository prodottiRepository, OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConv, OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConv, GiacenzeOutboundPort giacenzePort, OrdiniEventPublisherPort ordiniEventPublisher) {
		this.ordiniRepository = ordiniRepo;
		this.clientiRepository = clientiRepository;
		this.prodottiRepository = prodottiRepository;
		this.ordiniModelToOrdiniEntityConverter = ordiniModelToOrdiniEntityConv;
		this.ordiniEntityToOrdiniModelConverter = ordiniEntityToOrdiniModelConv;
		this.giacenzePort = giacenzePort;
		this.ordiniEventPublisher = ordiniEventPublisher;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CreaOrdineResponse creaOrdine(OrdineModel ordine) {
		
		validateOrdine(ordine);
		
		CreaOrdineResponse creaOrdineResponse = new CreaOrdineResponse();
		
		Collection<MagazzinoModel> magazziniDispOrdine = giacenzePort.getMagazziniConDisponibilitaProdotto(ordine.getIdProdotto(), ordine.getQuantitaOrdinata());
		
		if(!magazziniDispOrdine.isEmpty()) {
			
			TisOrdini ordineEntity = ordiniModelToOrdiniEntityConverter.convert(ordine);
			
			ordineEntity.setDataCreazione(LocalDateTime.now());
			
			ordineEntity.setDataAggiornamento(LocalDateTime.now());
			
			ordineEntity = ordiniRepository.save(ordineEntity);
			
			ordine = ordiniEntityToOrdiniModelConverter.convert(ordineEntity);
			creaOrdineResponse.setOrdine(ordine);
			creaOrdineResponse.setOrdineCreato(true);
			
			pubblicaEventoCreazioneOrdine(ordine, magazziniDispOrdine.stream().findFirst().orElseThrow(() -> new EntityNotFoundException("ERRORE CREAZIONE ORDINE - MAGAZZINO NON TROVATO")));
		
		}
		else {
			creaOrdineResponse.setOrdine(ordine);
			creaOrdineResponse.setOrdineCreato(false);
		}
		
		return creaOrdineResponse;
		
	}

	@Override
	@Transactional(readOnly = true)
	public OrdineModel getOrdine(Integer id) {
		
		OrdineModel result = null;
	
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		
		if(ordineEntity.isPresent()) {
			result = ordiniEntityToOrdiniModelConverter.convert(ordineEntity.get());
		}
		
		return result;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancellaOrdine(Integer id) {
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		if(ordineEntity.isPresent()) {
			ordiniRepository.delete(ordineEntity.get());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrdineModel aggiornaOrdine(OrdineModel ordine) {
		
		if(ordine.getIdCliente() == null || ordine.getIdProdotto() == null) {
			throw new IllegalArgumentException("ERRORE AGGIORNA ORDINE " + ordine.getId() + " - ID CLIENTE O ID PRODOTTO NON VALORIZZATI");
		}
		
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
			throw new EntityNotFoundException("ORDINE NON TROVATO ASSOCIATO A ID " + ordine.getId());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrdineModel> getAllOrdini() {
		
		List<OrdineModel> result = new LinkedList<>();
		
		List<TisOrdini> ordiniList = ordiniRepository.findAll();
		
		if(!ordiniList.isEmpty()) {
			ordiniList.stream().forEach(o -> result.add(ordiniEntityToOrdiniModelConverter.convert(o)));
		}
		
		return result;
		
	}
	
	private void validateOrdine(OrdineModel ordine) {
		if(ordine.getIdCliente() == null || ordine.getIdProdotto() == null) {
			throw new IllegalArgumentException("ERRORE CREAZIONE ORDINE - ID PRODOTTO O ID CLIENTE NON VALORIZZATI");
		}
		
		if(ordine.getQuantitaOrdinata().compareTo(0) <= 0) {
			throw new IllegalArgumentException("ERRORE CREAZIONE ORDINE - QUANTITA ORDINATA MINORE DI ZERO");
		}
	}
	
	private void pubblicaEventoCreazioneOrdine(OrdineModel ordine, MagazzinoModel magazzino) {
		OrdineCreatedEvent ordineCreatoEvent = new OrdineCreatedEvent(ordine, magazzino);
		ordiniEventPublisher.publish(ordineCreatoEvent);
	}

}
