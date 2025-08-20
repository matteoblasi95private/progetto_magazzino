package it.personalproject.auth.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIS_USER")
public class TisUser {
	
	@Id
	@Column(name = "Username", nullable = false)
	private String username;
	@Column(name = "Password", nullable = false)
    private String password;
	@Column(name = "Email")
    private String email;
	@Column(name = "PhoneNumber")
    private String phoneNumber;
	@Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;
	@Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "IdRuolo")
	private TisRole idRuolo;
    
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
		TisUser other = (TisUser) obj;
		return Objects.equals(username, other.username);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public TisRole getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(TisRole idRuolo) {
		this.idRuolo = idRuolo;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + "]";
	}
}
