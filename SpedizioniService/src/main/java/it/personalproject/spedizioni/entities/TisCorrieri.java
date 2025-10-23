package it.personalproject.spedizioni.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
    name = "TIS_CORRIERI",
    uniqueConstraints = {
        @UniqueConstraint(name = "UQ_TIS_CORRIERI_Nome", columnNames = "Nome")
    }
)
public class TisCorrieri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "Attivo", nullable = false)
    private Boolean attivo = true;
    
    @Column(name = "Sito")
    private String sito;
    
    @ManyToOne
    @JoinColumn(name = "IdStatoCorriere")
    private TfStatoCorriere idStatoCorriere;

    public TisCorrieri() {}

    public TisCorrieri(String nome, Boolean attivo) {
        this.nome = nome;
        this.attivo = attivo;
    }

    // --- getter/setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Boolean getAttivo() { return attivo; }
    public void setAttivo(Boolean attivo) { this.attivo = attivo; }

    public String getSito() {
		return sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TisCorrieri that)) return false;
        return Objects.equals(id, that.id) ||
               (id == null && Objects.equals(nome, that.nome));
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "TisCorrieri{id=%d, nome='%s', attivo=%s}"
            .formatted(id, nome, attivo);
    }
}