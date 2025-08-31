package it.personalproject.spedizioni.domain.ports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.personalproject.ordini.domain.events.OrdineEvent;

public interface OrdiniEventKafkaListenerPort {
	
	public void onEvent(OrdineEvent e) throws JsonMappingException, JsonProcessingException;

}
