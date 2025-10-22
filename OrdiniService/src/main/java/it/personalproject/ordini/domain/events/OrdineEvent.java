package it.personalproject.ordini.domain.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.personalproject.ordini.domain.OrdineModel;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @Type(value = OrdineCreatedEvent.class, name = "OrdineCreatedEvent")
})
public sealed abstract class OrdineEvent permits OrdineCreatedEvent, OrdineCancellatoEvent {
	
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
