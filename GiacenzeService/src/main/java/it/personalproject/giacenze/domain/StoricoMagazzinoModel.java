package it.personalproject.giacenze.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class StoricoMagazzinoModel {
	
	private Integer id;

    private MagazzinoModel magazzino;

    private ProdottiModel prodotto;
    
    private Integer quantitaMovimento;

    private String tipoMovimento;

    private LocalDateTime dataMovimento;

    private String riferimento;

    private String utente;

    private String note;

	public StoricoMagazzinoModel() {
		
	}

	

	public StoricoMagazzinoModel(Integer id, MagazzinoModel magazzino, ProdottiModel prodotto,
			Integer quantitaMovimento, String tipoMovimento, LocalDateTime dataMovimento, String riferimento,
			String utente, String note) {
		
		this.id = id;
		this.magazzino = magazzino;
		this.prodotto = prodotto;
		this.quantitaMovimento = quantitaMovimento;
		this.tipoMovimento = tipoMovimento;
		this.dataMovimento = dataMovimento;
		this.riferimento = riferimento;
		this.utente = utente;
		this.note = note;
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
		StoricoMagazzinoModel other = (StoricoMagazzinoModel) obj;
		return Objects.equals(id, other.id);
	}

	public MagazzinoModel getMagazzino() {
		return magazzino;
	}



	public void setMagazzino(MagazzinoModel magazzino) {
		this.magazzino = magazzino;
	}



	public ProdottiModel getProdotto() {
		return prodotto;
	}



	public void setProdotto(ProdottiModel prodotto) {
		this.prodotto = prodotto;
	}



	public Integer getQuantitaMovimento() {
		return quantitaMovimento;
	}

	public void setQuantitaMovimento(Integer quantitaMovimento) {
		this.quantitaMovimento = quantitaMovimento;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public LocalDateTime getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDateTime dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
    
}