package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.exceptions.SpedizioneNotFoundException;

public interface OrdineEventHandler {
	
	public void handleEvent(OrdineEvent e) throws CorrieriAttiviLiberiNotFoundException, SpedizioneNotFoundException;

}
