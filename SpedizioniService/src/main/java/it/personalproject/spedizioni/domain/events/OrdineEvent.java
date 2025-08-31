package it.personalproject.spedizioni.domain.events;

import it.personalproject.spedizioni.domain.OrdineModel;

public abstract class OrdineEvent {
	
	public OrdineModel ordine;

	public OrdineEvent(OrdineModel ordine) {
		this.ordine = ordine;
	}
	
}
