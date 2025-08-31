package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.OrdineModel;

public abstract class OrdineEvent {
	
	public OrdineModel ordine;

	public OrdineEvent(OrdineModel ordine) {
		this.ordine = ordine;
	}
	
}
