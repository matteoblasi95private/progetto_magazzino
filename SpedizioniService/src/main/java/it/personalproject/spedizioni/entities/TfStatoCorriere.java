package it.personalproject.spedizioni.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TF_STATO_CORRIERE")
public class TfStatoCorriere {
	
	@Id
	@Column(nullable = false)
	private Integer id;
	
	@Column(name = "Codice")
	private String codice;
	
	@Column(name = "Descrizione")
	private String descrizione;

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
		TfStatoCorriere other = (TfStatoCorriere) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "TfStatoCorriere [codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
