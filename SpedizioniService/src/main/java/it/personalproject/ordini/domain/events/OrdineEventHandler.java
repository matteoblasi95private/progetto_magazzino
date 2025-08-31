package it.personalproject.ordini.domain.events;

public interface OrdineEventHandler {
	
	public void handleEvent(OrdineEvent e);

}
