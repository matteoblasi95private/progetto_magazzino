package it.personalproject.ordini.domain.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.domain.SpedizioniService;

@Component
public class OrdineEventHandlerImpl implements OrdineEventHandler {
	
	@Autowired
	private SpedizioniService spedizioniService;

	@Override
	public void handleEvent(OrdineEvent e) throws CorrieriAttiviLiberiNotFoundException {
		
		switch(e) {
			case OrdineCreatedEvent o -> spedizioniService.creaSpedizioneFromOrdine(o.getOrdine());
			case OrdineCancellatoEvent o -> spedizioniService.cancellaSpedizione(o.getOrdine().getId());
		}
		
	}
	
}
