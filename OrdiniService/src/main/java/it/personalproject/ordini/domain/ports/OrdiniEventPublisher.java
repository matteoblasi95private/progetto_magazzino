package it.personalproject.ordini.domain.ports;

import it.personalproject.ordini.domain.events.OrdineEvent;

public interface OrdiniEventPublisher {
	
	public void publish(OrdineEvent ordineEvent);

}
