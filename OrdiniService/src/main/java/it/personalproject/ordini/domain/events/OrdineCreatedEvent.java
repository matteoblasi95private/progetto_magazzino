package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.OrdineModel;

public class OrdineCreatedEvent extends OrdineEvent {
	
	private MagazzinoModel magazzinoOrdine;

	public OrdineCreatedEvent(OrdineModel ordine) {
		super(ordine);
	}
	
	public OrdineCreatedEvent(OrdineModel ordine, MagazzinoModel magazzinoOrdine) {
		super(ordine);
		this.magazzinoOrdine = magazzinoOrdine;
	}

}
