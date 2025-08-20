package it.personalproject.auth.exception;

public class UserAlreadyPresentException extends Exception {
	
	public UserAlreadyPresentException() {

	}
	
	public UserAlreadyPresentException(String message) {
		super(message);
	}

}
