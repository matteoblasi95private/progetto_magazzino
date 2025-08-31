package it.personalproject.spedizioni.domain.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.personalproject.spedizioni.domain.SpedizioniService;

@Component
public class OrdineEventHandlerImpl implements OrdineEventHandler {
	
	@Autowired
	private SpedizioniService spedizioniService;

	@Override
	public void handleEvent(OrdineEvent e) {
		
		if(e instanceof OrdineCreatedEvent) {
			
			//TODO: DA CONTINUARE
			spedizioniService.creaSpedizione(null);
		}
		else {
			throw new IllegalArgumentException("ORDINE EVENT HANDLER - EVENTO NON CONTEMPLATO: " + e);
		}
		
	}

}
