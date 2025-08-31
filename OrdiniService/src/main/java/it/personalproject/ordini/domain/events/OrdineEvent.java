package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.OrdineModel;

public abstract class OrdineEvent {
	
	protected OrdineModel ordine;

	public OrdineEvent(OrdineModel ordine) {
		this.ordine = ordine;
	}

	public OrdineModel getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineModel ordine) {
		this.ordine = ordine;
	}
	
	
	
}
