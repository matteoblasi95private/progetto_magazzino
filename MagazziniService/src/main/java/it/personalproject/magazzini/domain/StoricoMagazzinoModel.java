package it.personalproject.magazzini.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class StoricoMagazzinoModel {
	
	private Integer id;

    private Integer idMagazzino;

    private Integer idProdotto;
    
    private Integer quantitaMovimento;

    private String tipoMovimento;

    private LocalDateTime dataMovimento;

    private String riferimento;

    private String utente;

    private String note;

	public StoricoMagazzinoModel() {
		super();
	}

	public StoricoMagazzinoModel(Integer id, Integer idMagazzino, Integer idProdotto, Integer quantitaMovimento,
			String tipoMovimento, LocalDateTime dataMovimento, String riferimento, String utente, String note) {
		super();
		this.id = id;
		this.idMagazzino = idMagazzino;
		this.idProdotto = idProdotto;
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

	public Integer getIdMagazzino() {
		return idMagazzino;
	}

	public void setIdMagazzino(Integer idMagazzino) {
		this.idMagazzino = idMagazzino;
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
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