package it.personalproject.auth.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import it.personalproject.auth.exception.RoleNotFoundException;
import it.personalproject.auth.exception.UserAlreadyPresentException;

public interface AuthService {
	
	public LoginResponse login(LoginDTO loginDTO);
	
	public LoginResponse register(RegisterDTO registerDTO) throws UserAlreadyPresentException, RoleNotFoundException;
	
	public String generateToken(String username, Collection<? extends GrantedAuthority> authorities);

}
