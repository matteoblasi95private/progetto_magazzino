package it.personalproject.ordini.domain.ports;

import it.personalproject.ordini.domain.events.OrdineEvent;

public interface OrdiniEventPublisherPort {
	
	public void publish(OrdineEvent ordineEvent);

}
