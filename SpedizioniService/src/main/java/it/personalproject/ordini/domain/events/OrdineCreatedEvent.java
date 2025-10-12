package it.personalproject.ordini.domain.events;

import it.personalproject.spedizioni.domain.MagazzinoModel;
import it.personalproject.spedizioni.domain.OrdineModel;

public final class OrdineCreatedEvent extends OrdineEvent {
	
	private final MagazzinoModel magazzinoOrdine;
	
	public OrdineCreatedEvent(OrdineModel ordine, MagazzinoModel magazzinoOrdine) {
		super(ordine);
		this.magazzinoOrdine = magazzinoOrdine;
	}

	public MagazzinoModel getMagazzinoOrdine() {
		return magazzinoOrdine;
	}

	@Override
	public String toString() {
		return "OrdineCreatedEvent [magazzinoOrdine=" + magazzinoOrdine + ", ordine=" + ordine + "]";
	}
	
	

}
