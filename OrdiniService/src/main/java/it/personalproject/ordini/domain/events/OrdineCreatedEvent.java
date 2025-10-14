package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.OrdineModel;

public final class OrdineCreatedEvent extends OrdineEvent {
	
	private MagazzinoModel magazzinoOrdine;
	
	public OrdineCreatedEvent() {
		
	}

	public OrdineCreatedEvent(OrdineModel ordine) {
		super(ordine);
	}
	
	public OrdineCreatedEvent(OrdineModel ordine, MagazzinoModel magazzinoOrdine) {
		super(ordine);
		this.setMagazzinoOrdine(magazzinoOrdine);
	}

	public MagazzinoModel getMagazzinoOrdine() {
		return magazzinoOrdine;
	}

	public void setMagazzinoOrdine(MagazzinoModel magazzinoOrdine) {
		this.magazzinoOrdine = magazzinoOrdine;
	}

	@Override
	public String toString() {
		return "OrdineCreatedEvent [magazzinoOrdine=" + magazzinoOrdine + ", ordine=" + ordine + "]";
	}
	
	

}
