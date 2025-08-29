package it.personalproject.giacenze.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class GiacenzeModel {
	
	private ProdottiModel prodotto;
	private MagazzinoModel magazzino;
	private Integer quantitaDisponibile;
	private LocalDateTime dataAggiornamento;
	
	public GiacenzeModel() {
		
	}
	
	public GiacenzeModel(ProdottiModel prodotto, MagazzinoModel magazzino, Integer quantitaDisponibile,
			LocalDateTime dataAggiornamento) {
		super();
		this.prodotto = prodotto;
		this.magazzino = magazzino;
		this.quantitaDisponibile = quantitaDisponibile;
		this.dataAggiornamento = dataAggiornamento;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(magazzino, prodotto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiacenzeModel other = (GiacenzeModel) obj;
		return Objects.equals(magazzino, other.magazzino) && Objects.equals(prodotto, other.prodotto);
	}

	public ProdottiModel getProdotto() {
		return prodotto;
	}
	public void setProdotto(ProdottiModel prodotto) {
		this.prodotto = prodotto;
	}
	public MagazzinoModel getMagazzino() {
		return magazzino;
	}
	public void setMagazzino(MagazzinoModel magazzino) {
		this.magazzino = magazzino;
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
	@Override
	public String toString() {
		return "GiacenzeModel [prodotto=" + prodotto + ", magazzino=" + magazzino + ", quantitaDisponibile="
				+ quantitaDisponibile + "]";
	}
	
}
