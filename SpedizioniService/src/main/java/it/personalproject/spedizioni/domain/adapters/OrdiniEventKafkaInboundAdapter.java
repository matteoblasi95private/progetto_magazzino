package it.personalproject.spedizioni.domain.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import it.personalproject.spedizioni.domain.events.OrdineEvent;
import it.personalproject.spedizioni.domain.events.OrdineEventHandler;
import it.personalproject.spedizioni.domain.ports.OrdiniEventKafkaListenerPort;

@Component
public class OrdiniEventKafkaInboundAdapter implements OrdiniEventKafkaListenerPort {
	
	private final OrdineEventHandler ordineEventHandler;

	@Autowired
    public OrdiniEventKafkaInboundAdapter(OrdineEventHandler ordineEventHandler) {
        this.ordineEventHandler = ordineEventHandler;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.channels.ordini}", groupId="${spring.kafka.consumer.group-id}")
    public void onEvent(OrdineEvent e) {
        ordineEventHandler.handleEvent(e);
    }

}
