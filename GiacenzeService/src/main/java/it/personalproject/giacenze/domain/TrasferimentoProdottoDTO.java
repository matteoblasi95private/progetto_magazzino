package it.personalproject.giacenze.domain;

import java.util.Objects;

public class TrasferimentoProdottoDTO {
	
	private Integer idProdotto;
	private Integer idMagazzinoPrecedente;
	private Integer idMagazzinoNuovo;
	private Integer quantitaTrasferita;
	
	
	
	public TrasferimentoProdottoDTO() {
	
	}
	
	public TrasferimentoProdottoDTO(Integer idProdotto, Integer idMagazzinoPrecedente, Integer idMagazzinoNuovo,
			Integer quantitaTrasferita) {
		this.idProdotto = idProdotto;
		this.idMagazzinoPrecedente = idMagazzinoPrecedente;
		this.idMagazzinoNuovo = idMagazzinoNuovo;
		this.quantitaTrasferita = quantitaTrasferita;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idMagazzinoNuovo, idMagazzinoPrecedente, idProdotto, quantitaTrasferita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrasferimentoProdottoDTO other = (TrasferimentoProdottoDTO) obj;
		return Objects.equals(idMagazzinoNuovo, other.idMagazzinoNuovo)
				&& Objects.equals(idMagazzinoPrecedente, other.idMagazzinoPrecedente)
				&& Objects.equals(idProdotto, other.idProdotto)
				&& Objects.equals(quantitaTrasferita, other.quantitaTrasferita);
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	public Integer getIdMagazzinoPrecedente() {
		return idMagazzinoPrecedente;
	}
	public void setIdMagazzinoPrecedente(Integer idMagazzinoPrecedente) {
		this.idMagazzinoPrecedente = idMagazzinoPrecedente;
	}
	public Integer getIdMagazzinoNuovo() {
		return idMagazzinoNuovo;
	}
	public void setIdMagazzinoNuovo(Integer idMagazzinoNuovo) {
		this.idMagazzinoNuovo = idMagazzinoNuovo;
	}
	public Integer getQuantitaTrasferita() {
		return quantitaTrasferita;
	}
	public void setQuantitaTrasferita(Integer quantitaTrasferita) {
		this.quantitaTrasferita = quantitaTrasferita;
	}

	@Override
	public String toString() {
		return "TrasferimentoProdottoDTO [idProdotto=" + idProdotto + ", idMagazzinoPrecedente=" + idMagazzinoPrecedente
				+ ", idMagazzinoNuovo=" + idMagazzinoNuovo + ", quantitaTrasferita=" + quantitaTrasferita + "]";
	}
	
	
	

}
