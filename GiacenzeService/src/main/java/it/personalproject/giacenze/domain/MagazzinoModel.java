package it.personalproject.giacenze.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class MagazzinoModel {

    private Integer id;
    private String codice;
    private String nome;
    private String indirizzo;
    private String citta;
    private String paese;
    private Boolean attivo;
    private LocalDateTime dataCreazione;

    public MagazzinoModel() {}

    public MagazzinoModel(Integer id, String codice, String nome, String indirizzo, String citta, String cap, String paese,
                          String telefono, String email, Boolean attivo,
                          LocalDateTime dataCreazione, LocalDateTime dataAggiornamento) {
        this.id = id;
        this.nome = nome;
        this.codice = codice;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.paese = paese;
        this.attivo = attivo;
        this.dataCreazione = dataCreazione;
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
		MagazzinoModel other = (MagazzinoModel) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getPaese() { return paese; }
    public void setPaese(String paese) { this.paese = paese; }

    public Boolean getAttivo() { return attivo; }
    public void setAttivo(Boolean attivo) { this.attivo = attivo; }

    public LocalDateTime getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(LocalDateTime dataCreazione) { this.dataCreazione = dataCreazione; }
    
    @Override
    public String toString() {
        return "MagazzinoModel{id=%d, nome='%s', citta='%s', attivo=%s}"
            .formatted(id, nome, citta, attivo);
    }

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
}