package it.personalproject.spedizioni.domain.ports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.personalproject.ordini.domain.events.OrdineEvent;
import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.exceptions.SpedizioneNotFoundException;

public interface OrdiniEventKafkaListenerPort {
	
	public void onEvent(OrdineEvent e) throws JsonMappingException, JsonProcessingException, CorrieriAttiviLiberiNotFoundException, SpedizioneNotFoundException;

}
