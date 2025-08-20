package it.personalproject.auth.domain;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MagazzinoUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1003105287725533997L;
	
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	
	public MagazzinoUserDetails() {
		
	}
	
	public MagazzinoUserDetails(String username, String email, String password, String phoneNumber,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.authorities = authorities;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagazzinoUserDetails other = (MagazzinoUserDetails) obj;
		return Objects.equals(username, other.username);
	}

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
	

}
