package it.personalproject.spedizioni.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class StoricoSpedizioniModel {
	
	private Integer id;
	private Integer idSpedizione;
	private Integer idStato;
	private String descrizione;
	private LocalDateTime dataEvento;
	
	public StoricoSpedizioniModel() {
		
	}
	
	public StoricoSpedizioniModel(Integer id, Integer idSpedizione, Integer idStato, String descrizione,
			LocalDateTime dataEvento) {
		super();
		this.id = id;
		this.idSpedizione = idSpedizione;
		this.idStato = idStato;
		this.descrizione = descrizione;
		this.dataEvento = dataEvento;
	}



	@Override
	public int hashCode() {
		return Objects.hash(dataEvento, descrizione, id, idSpedizione, idStato);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoricoSpedizioniModel other = (StoricoSpedizioniModel) obj;
		return Objects.equals(id, other.id);
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdSpedizione() {
		return idSpedizione;
	}
	public void setIdSpedizione(Integer idSpedizione) {
		this.idSpedizione = idSpedizione;
	}
	public Integer getIdStato() {
		return idStato;
	}
	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDateTime getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(LocalDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	
	
	

}
