package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.OrdineModel;

public final class OrdineCancellatoEvent extends OrdineEvent {
	
	public OrdineCancellatoEvent(OrdineModel o) {
		super(o);
	}

}
