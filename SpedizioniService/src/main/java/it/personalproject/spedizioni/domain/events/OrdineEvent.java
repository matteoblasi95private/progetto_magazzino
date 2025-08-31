package it.personalproject.spedizioni.domain.events;

import it.personalproject.spedizioni.domain.OrdineModel;

public abstract class OrdineEvent {
	
	protected OrdineModel ordine;

	public OrdineEvent(OrdineModel ordine) {
		this.setOrdine(ordine);
	}

	public OrdineModel getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineModel ordine) {
		this.ordine = ordine;
	}
	
}
