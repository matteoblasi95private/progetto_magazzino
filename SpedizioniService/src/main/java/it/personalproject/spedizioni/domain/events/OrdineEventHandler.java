package it.personalproject.spedizioni.domain.events;

public interface OrdineEventHandler {
	
	public void handleEvent(OrdineEvent e);

}
