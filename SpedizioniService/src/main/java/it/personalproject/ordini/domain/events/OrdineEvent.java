package it.personalproject.ordini.domain.events;

import it.personalproject.spedizioni.domain.OrdineModel;

public abstract class OrdineEvent {
	
	protected OrdineModel ordine;
	
	public OrdineEvent() {
		
	}

	public OrdineEvent(OrdineModel ordine) {
		this.ordine = ordine;
	}

	public OrdineModel getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineModel ordine) {
		this.ordine = ordine;
	}

	@Override
	public String toString() {
		return "OrdineEvent [ordine=" + ordine + "]";
	}
	
	
	
}
