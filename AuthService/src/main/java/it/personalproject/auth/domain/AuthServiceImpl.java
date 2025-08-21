package it.personalproject.auth.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.personalproject.auth.entities.TisRole;
import it.personalproject.auth.entities.TisUser;
import it.personalproject.auth.exception.RoleNotFoundException;
import it.personalproject.auth.exception.UserAlreadyPresentException;
import it.personalproject.auth.repositories.RoleRepository;
import it.personalproject.auth.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public LoginResponse login(LoginDTO loginDTO) {
		
		//logger.info("LOGIN UTENTE " + loginDTO);
		
		UsernamePasswordAuthenticationToken usernamePassword = UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword());
		Authentication auth = authenticationManager.authenticate(usernamePassword);
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		String jwtToken = generateToken(userDetails.getUsername(), userDetails.getAuthorities());
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setAccessToken(jwtToken);
		
		return loginResponse;
		
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public LoginResponse register(RegisterDTO registerDTO) throws UserAlreadyPresentException, RoleNotFoundException {
		
		//logger.info("REGISTRAZIONE UTENTE " + registerDTO);
		
		Optional<TisUser> userOptional = userRepository.findById(registerDTO.getUsername());
		
		if(userOptional.isPresent()) {
			throw new UserAlreadyPresentException("ERRORE REGISTRAZIONE UTENTE [USERNAME: " + registerDTO.getUsername() + "] - UTENTE GIA PRESENTE");
		}
		
		TisUser newUser = new TisUser();
		
		newUser.setEmail(registerDTO.getEmail());
		newUser.setPhoneNumber(registerDTO.getPhoneNumber());
		newUser.setUsername(registerDTO.getUsername());
		newUser.setCreatedAt(LocalDateTime.now());
		newUser.setUpdatedAt(LocalDateTime.now());
		newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		
		TisRole ruoloUser = roleRepository.findByName("USER").orElseThrow(() -> new RoleNotFoundException("RUOLO ASSOCIATO A USER NON TROVATO"));
		
		newUser.setIdRuolo(ruoloUser);
		
		userRepository.save(newUser);
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsername(registerDTO.getUsername());
		loginDTO.setPassword(registerDTO.getPassword());
		
		return login(loginDTO);
		
	}

	@Override
	public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
		return jwtService.generateToken(username, authorities);
	}

}
