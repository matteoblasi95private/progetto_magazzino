package it.personalproject.ordini.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIS_ORDINI")
public class TisOrdini {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	public Integer id;
	
	@ManyToOne
	@JoinColumn(name = "IdCliente")
	private TisClienti idCliente;
	
	@ManyToOne
	@JoinColumn(name = "IdProdotto")
	private TisProdotti idProdotto;
	
	@ManyToOne
	@JoinColumn(name = "IdStatoOrdine")
	private TfStatoOrdine idStatoOrdine;
	
	@Column(name = "QuantitaOrdinata", nullable = false)
	private Integer quantitaOrdinata;
	
	@Column(name = "dataCreazione", updatable = false)
	private LocalDateTime dataCreazione;
	
	@Column(name = "dataAggiornamento")
	private LocalDateTime dataAggiornamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TisClienti getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(TisClienti idCliente) {
		this.idCliente = idCliente;
	}

	public TisProdotti getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(TisProdotti idProdotto) {
		this.idProdotto = idProdotto;
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

	public Integer getQuantitaOrdinata() {
		return quantitaOrdinata;
	}

	public void setQuantitaOrdinata(Integer quantitaOrdinata) {
		this.quantitaOrdinata = quantitaOrdinata;
	}

	public TfStatoOrdine getIdStatoOrdine() {
		return idStatoOrdine;
	}

	public void setIdStatoOrdine(TfStatoOrdine idStatoOrdine) {
		this.idStatoOrdine = idStatoOrdine;
	}
	
	
	

}
