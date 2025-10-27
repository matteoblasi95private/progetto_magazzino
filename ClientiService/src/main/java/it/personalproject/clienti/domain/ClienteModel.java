package it.personalproject.clienti.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ClienteModel {
	
	private Integer id;
	@NotNull
	private String codiceFiscale;
	@NotNull
	private String nome;
	@NotNull
	private String cognome;
	@NotNull
	@Email
	private String email;
	private String telefono;
	@NotNull
	private String indirizzo;
	@NotNull
	private String citta;
	@NotNull
	private String cap;
	@NotNull
	private String paese;
	
	@NotNull
	private Boolean attivo;
	
	
	private LocalDateTime dataRegistrazione;
	
	
	public ClienteModel() {
		
	}
	
	public ClienteModel(Integer id, @NotNull String codiceFiscale, @NotNull String nome, @NotNull String cognome,
			@NotNull @Email String email, String telefono, @NotNull String indirizzo, @NotNull String citta,
			@NotNull String cap, @NotNull String paese) {
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.cap = cap;
		this.paese = paese;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteModel other = (ClienteModel) obj;
		return Objects.equals(id, other.id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public LocalDateTime getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(LocalDateTime dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	
	
		
}
