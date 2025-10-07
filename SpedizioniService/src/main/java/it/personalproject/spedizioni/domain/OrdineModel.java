package it.personalproject.spedizioni.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrdineModel {
	
	@NotNull
	@Positive
	private Integer id;
	
	@NotNull
	@Positive
	private Integer idCliente;
	
	@NotNull
	@Positive
	private Integer idProdotto;
	
	@NotNull
	@Positive
	private Integer quantitaOrdinata;
	
	@NotNull
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

	public Integer getQuantitaOrdinata() {
		return quantitaOrdinata;
	}

	public void setQuantitaOrdinata(Integer quantitaOrdinata) {
		this.quantitaOrdinata = quantitaOrdinata;
	}

	@Override
	public String toString() {
		return "OrdineModel [id=" + id + ", idCliente=" + idCliente + ", idProdotto=" + idProdotto
				+ ", quantitaOrdinata=" + quantitaOrdinata + ", statoOrdine=" + statoOrdine + ", dataCreazione="
				+ dataCreazione + ", dataAggiornamento=" + dataAggiornamento + "]";
	}
	
	
	

}
