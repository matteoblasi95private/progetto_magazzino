package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;

public interface OrdineEventHandler {
	
	public void handleEvent(OrdineEvent e) throws CorrieriAttiviLiberiNotFoundException;

}
