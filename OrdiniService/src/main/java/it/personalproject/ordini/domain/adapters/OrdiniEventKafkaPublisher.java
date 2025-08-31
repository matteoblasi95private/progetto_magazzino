package it.personalproject.ordini.domain.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import it.personalproject.ordini.domain.events.OrdineEvent;
import it.personalproject.ordini.domain.ports.OrdiniEventPublisher;

@Component
public class OrdiniEventKafkaPublisher implements OrdiniEventPublisher {
	
	@Value("${kafka.ordini.channel}")
	private String ordiniChannel;
	
	@Autowired
	private KafkaTemplate<String, OrdineEvent> kafkaTemplate;

	@Override
	public void publish(OrdineEvent ordineEvent) {
		kafkaTemplate.send(ordiniChannel, ordineEvent);	
	}

}
