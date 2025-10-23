package it.personalproject.spedizioni.domain.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.personalproject.ordini.domain.events.OrdineEvent;
import it.personalproject.ordini.domain.events.OrdineEventHandler;
import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.controller.ExceptionController;
import it.personalproject.spedizioni.domain.ports.OrdiniEventKafkaListenerPort;

@Component
public class OrdiniEventKafkaInboundAdapter implements OrdiniEventKafkaListenerPort {
	
    private static final Logger log = LoggerFactory.getLogger(OrdiniEventKafkaInboundAdapter.class);
	
	private final OrdineEventHandler ordineEventHandler;

	@Autowired
    public OrdiniEventKafkaInboundAdapter(OrdineEventHandler ordineEventHandler) {
        this.ordineEventHandler = ordineEventHandler;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.channels.ordini}", groupId="${spring.kafka.consumer.group-id}")
    public void onEvent(OrdineEvent e) throws CorrieriAttiviLiberiNotFoundException {
		log.info("ORDINI SERVICE - CONSUMER - TOPIC ORDINI - RICEVUTO EVENTO: {}", e);
        ordineEventHandler.handleEvent(e);
    }

}
