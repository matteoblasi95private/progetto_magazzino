package it.personalproject.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.personalproject.auth.domain.AuthService;
import it.personalproject.auth.domain.LoginDTO;
import it.personalproject.auth.domain.LoginResponse;
import it.personalproject.auth.domain.RegisterDTO;
import it.personalproject.auth.exception.RoleNotFoundException;
import it.personalproject.auth.exception.UserAlreadyPresentException;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<LoginResponse> register(RegisterDTO registerDTO) throws UserAlreadyPresentException, RoleNotFoundException {
		
		return ResponseEntity.status(HttpStatus.OK).body(authService.register(registerDTO));
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
		
		return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginDTO));
		
	}

}
