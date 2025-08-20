package it.personalproject.auth.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.personalproject.auth.entities.TisUser;
import it.personalproject.auth.repositories.UserRepository;


public class MagazzinoUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		TisUser user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("USERNAME NON TROVATO: " + username));
		
		MagazzinoUserDetails userDetails = new MagazzinoUserDetails();
		
		userDetails.setEmail(user.getEmail());
		userDetails.setPhoneNumber(user.getPhoneNumber());
		userDetails.setPassword(user.getPassword());
		userDetails.setUsername(user.getUsername());
		
		userDetails.setAuthorities(List.of(new SimpleGrantedAuthority(user.getIdRuolo().getName())));
		
		return userDetails;
		
	}

}
