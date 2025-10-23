package it.personalproject.ordini.domain.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.domain.ports.OrdiniEventPublisherPort;

@Component
public class OrdineEventHandler {
	
	private final OrdiniEventPublisherPort ordiniEventPublisher;
	
	@Autowired
	public OrdineEventHandler(OrdiniEventPublisherPort ordiniEventPublisher) {
		this.ordiniEventPublisher = ordiniEventPublisher;
	}
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void pubblicaEventoCreazioneOrdine(OrdineCreatedEvent ordineCreatoEvent) {
		ordiniEventPublisher.publish(ordineCreatoEvent);
	}
	
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void pubblicaEventoCancellazioneOrdine(OrdineCancellatoEvent e) {
		ordiniEventPublisher.publish(e);
	}

}
