package it.personalproject.ordini.domain.events;

import it.personalproject.spedizioni.domain.OrdineModel;

public final class OrdineCancellatoEvent extends OrdineEvent {
	
	public OrdineCancellatoEvent() {
		super();
	}

	public OrdineCancellatoEvent(OrdineModel ordine) {
		super(ordine);
	}

}
