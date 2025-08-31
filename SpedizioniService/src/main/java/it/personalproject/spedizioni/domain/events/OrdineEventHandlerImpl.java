package it.personalproject.spedizioni.domain.events;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.personalproject.spedizioni.domain.SpedizioneModel;
import it.personalproject.spedizioni.domain.SpedizioniService;

@Component
public class OrdineEventHandlerImpl implements OrdineEventHandler {
	
	@Autowired
	private SpedizioniService spedizioniService;

	@Override
	public void handleEvent(OrdineEvent e) {
		
		if(e instanceof OrdineCreatedEvent) {
			
			SpedizioneModel spedizione = new SpedizioneModel();
			
			spedizione.setIdOrdine(e.getOrdine().getId());
			spedizione.setIdCliente(e.getOrdine().getIdCliente());
			spedizione.setTrackingNumber("1");
			spedizione.setDataConsegnaPrevista(LocalDateTime.now().plusDays(1));
			spedizione.setDestCitta("Roma");
			spedizione.setDestProvincia("RM");
			spedizione.setDataCreazione(LocalDateTime.now());
			spedizione.setDestIndirizzo("Via Test 1");
			spedizione.setDestNome("test");
			spedizione.setDestCap("00118");
			spedizione.setIdStato(1);
			
			spedizioniService.creaSpedizione(spedizione);
		}
		else {
			throw new IllegalArgumentException("ORDINE EVENT HANDLER - EVENTO NON CONTEMPLATO: " + e);
		}
		
	}

}
