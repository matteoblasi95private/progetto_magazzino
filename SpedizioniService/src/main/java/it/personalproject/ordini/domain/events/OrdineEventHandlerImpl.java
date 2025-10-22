package it.personalproject.ordini.domain.events;

import java.math.BigDecimal;
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
		
		switch(e) {
			case OrdineCreatedEvent o -> spedizioniService.creaSpedizione(creaSpedizioneFromOrdine(o));
			case OrdineCancellatoEvent o -> spedizioniService.cancellaSpedizione(o.getOrdine().getId());
		}
		
	}
	
	private SpedizioneModel creaSpedizioneFromOrdine(OrdineEvent e) {
		
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
		spedizione.setCostoSpedizione(BigDecimal.valueOf(30));
		
		return spedizione;
		
		
	}
	
}
