package it.personalproject.auth.exception;

public class RoleNotFoundException extends RuntimeException {
	
	public RoleNotFoundException() {
		
	}
	
	public RoleNotFoundException(String message) {
		super(message);
	}

}
