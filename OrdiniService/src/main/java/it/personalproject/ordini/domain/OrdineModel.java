package it.personalproject.ordini.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class OrdineModel {
	
	private Integer id;
	private Integer idCliente;
	private Integer idProdotto;
	private StatoOrdine statoOrdine;
	private LocalDateTime dataCreazione;
	private LocalDateTime dataAggiornamento;
	
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
		OrdineModel other = (OrdineModel) obj;
		return Objects.equals(id, other.id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public StatoOrdine getStatoOrdine() {
		return statoOrdine;
	}
	public void setStatoOrdine(StatoOrdine statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public LocalDateTime getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	
	

}
