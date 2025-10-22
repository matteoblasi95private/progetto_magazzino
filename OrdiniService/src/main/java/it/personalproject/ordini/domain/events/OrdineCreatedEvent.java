package it.personalproject.ordini.domain.events;

import it.personalproject.ordini.domain.MagazzinoModel;
import it.personalproject.ordini.domain.OrdineModel;
import jakarta.validation.constraints.NotNull;

public final class OrdineCreatedEvent extends OrdineEvent {
	
	@NotNull
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
