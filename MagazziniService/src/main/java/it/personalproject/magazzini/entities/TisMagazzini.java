package it.personalproject.magazzini.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
    name = "TIS_MAGAZZINO",
    uniqueConstraints = {
        @UniqueConstraint(name = "UQ_MAGAZZINO_NOME", columnNames = "Codice")
    }
)
public class TisMagazzini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Codice", nullable = false)
    private String codice;
    
    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "Indirizzo", length = 255)
    private String indirizzo;

    @Column(name = "Citta", length = 100)
    private String citta;

    @Column(name = "Paese", length = 100)
    private String paese;

    @Column(name = "Attivo", nullable = false)
    private Boolean attivo = true;

    @Column(name = "DataCreazione", updatable = false)
    private LocalDateTime dataCreazione;

    public TisMagazzini() {}

    public TisMagazzini(String codice, String nome, String indirizzo, String citta, String paese) {
        this.nome = nome;
        this.codice = codice;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.paese = paese;
        this.dataCreazione = LocalDateTime.now();
    }

    // --- getter e setter ---
    
    public Integer getId() { return id; }
    
    public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TisMagazzini that)) return false;
        return Objects.equals(id, that.id) || Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id != null ? id : nome);
    }

    @Override
    public String toString() {
        return "TisMagazzino{id=%d, nome='%s', citta='%s'}"
            .formatted(id, nome, citta);
    }
}