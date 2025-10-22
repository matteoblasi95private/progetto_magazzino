package it.personalproject.ordini.domain.events;

import it.personalproject.spedizioni.domain.OrdineModel;
import jakarta.validation.constraints.NotNull;

public abstract sealed class OrdineEvent permits OrdineCreatedEvent, OrdineCancellatoEvent {
	
	@NotNull
	protected final OrdineModel ordine;

	public OrdineEvent(OrdineModel ordine) {
		this.ordine = ordine;
	}

	public OrdineModel getOrdine() {
		return ordine;
	}

	@Override
	public String toString() {
		return "OrdineEvent [ordine=" + ordine + "]";
	}
	
	
	
}
