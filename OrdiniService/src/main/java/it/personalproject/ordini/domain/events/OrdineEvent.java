package it.personalproject.ordini.domain.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.personalproject.ordini.domain.OrdineModel;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @Type(value = OrdineCreatedEvent.class, name = "OrdineCreatedEvent")
})
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
