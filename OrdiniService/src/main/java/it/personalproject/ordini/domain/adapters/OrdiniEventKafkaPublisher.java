package it.personalproject.ordini.domain.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import it.personalproject.ordini.domain.events.OrdineEvent;
import it.personalproject.ordini.domain.ports.OrdiniEventPublisherPort;

@Component
public class OrdiniEventKafkaPublisher implements OrdiniEventPublisherPort {
	
    private static final Logger log = LoggerFactory.getLogger(OrdiniEventKafkaPublisher.class);
	
	@Value("${spring.kafka.channels.ordini}")
	private String ordiniChannel;
	
	@Autowired
	private KafkaTemplate<String, OrdineEvent> kafkaTemplate;

	@Override
	public void publish(OrdineEvent ordineEvent) {
		log.info("ORDINI SERVICE - TOPIC ORDINI - PRODUCER - PUBBLICAZIONE EVENTO: {}",  ordineEvent);
		kafkaTemplate.send(ordiniChannel, ordineEvent);	
	}

}
