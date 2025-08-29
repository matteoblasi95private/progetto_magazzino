package it.personalproject.giacenze.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;

@Entity
@Table(name = "TIS_GIACENZE")
public class TisGiacenze {
	
	@EmbeddedId
	private TisGiacenzePK giacenzePK;

	@MapsId("IdMagazzino")
	@ManyToOne
	@JoinColumn(name = "IdMagazzino")
	private TisMagazzini magazzino;

	@MapsId("IdProdotto")
	@ManyToOne
	@JoinColumn(name = "IdProdotto")
	private TisProdotti prodotto;
	
	@Column(name = "QuantitaDisponibile", nullable = false)
	private Integer quantitaDisponibile;
	
	
	@Column(name = "DataAggiornamento")
    private LocalDateTime dataAggiornamento;
	
	

	public TisGiacenze() {
		
	}	

	public TisGiacenze(TisGiacenzePK giacenzePK, TisMagazzini magazzino, TisProdotti prodotto,
			Integer quantitaDisponibile, LocalDateTime dataAggiornamento) {
		super();
		this.giacenzePK = giacenzePK;
		this.magazzino = magazzino;
		this.prodotto = prodotto;
		this.quantitaDisponibile = quantitaDisponibile;
		this.dataAggiornamento = dataAggiornamento;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(giacenzePK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TisGiacenze other = (TisGiacenze) obj;
		return Objects.equals(giacenzePK, other.giacenzePK);
	}

	public TisGiacenzePK getGiacenzePK() {
		return giacenzePK;
	}


	public void setGiacenzePK(TisGiacenzePK giacenzePK) {
		this.giacenzePK = giacenzePK;
	}


	public TisMagazzini getMagazzino() {
		return magazzino;
	}


	public void setMagazzino(TisMagazzini magazzino) {
		this.magazzino = magazzino;
	}


	public TisProdotti getProdotto() {
		return prodotto;
	}


	public void setProdotto(TisProdotti prodotto) {
		this.prodotto = prodotto;
	}


	public Integer getQuantitaDisponibile() {
		return quantitaDisponibile;
	}


	public void setQuantitaDisponibile(Integer quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}


	public LocalDateTime getDataAggiornamento() {
		return dataAggiornamento;
	}


	public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	

}
