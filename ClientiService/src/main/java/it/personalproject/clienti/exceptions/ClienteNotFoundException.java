package it.personalproject.clienti.exceptions;

public class ClienteNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -523835922129063846L;

	public ClienteNotFoundException() {
		
	}
	
	public ClienteNotFoundException(String message) {
		super(message);
	}

}
