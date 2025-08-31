package it.personalproject.spedizioni.domain.events;

import it.personalproject.spedizioni.domain.MagazzinoModel;
import it.personalproject.spedizioni.domain.OrdineModel;

public class OrdineCreatedEvent extends OrdineEvent {
	
	private MagazzinoModel magazzinoOrdine;

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

}
