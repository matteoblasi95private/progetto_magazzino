package it.personalproject.spedizioni.domain.ports;

import it.personalproject.spedizioni.domain.events.OrdineEvent;

public interface OrdiniEventKafkaListenerPort {
	
	public void onEvent(OrdineEvent e);

}
