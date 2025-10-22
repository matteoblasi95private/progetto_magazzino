package it.personalproject.ordini.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TF_STATO_ORDINE")
public class TfStatoOrdine {
	
	@Id
	@Column(name = "Id", nullable = false)
	private Integer id;
	
	@Column(name = "Codice", nullable = false, length = 50)
	private String codice;
	
	@Column(name = "Descrizione", length = 60)
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
		TfStatoOrdine other = (TfStatoOrdine) obj;
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
		return "TfStatoOrdine [codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
