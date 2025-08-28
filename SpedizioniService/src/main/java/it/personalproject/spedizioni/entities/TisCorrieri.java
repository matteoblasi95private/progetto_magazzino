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

    @Column(name = "Telefono", length = 30)
    private String telefono;

    @Column(name = "Email", length = 150)
    private String email;

    @Column(name = "Attivo", nullable = false)
    private Boolean attivo = true;

    @Column(name = "DataCreazione", updatable = false)
    private LocalDateTime dataCreazione;

    @Column(name = "DataAggiornamento")
    private LocalDateTime dataAggiornamento;

    public TisCorrieri() {}

    public TisCorrieri(String nome, String telefono, String email, Boolean attivo) {
        this.nome = nome;
        this.telefono = telefono;
        this.email = email;
        this.attivo = attivo;
        this.dataCreazione = LocalDateTime.now();
        this.dataAggiornamento = LocalDateTime.now();
    }

    // --- getter/setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getAttivo() { return attivo; }
    public void setAttivo(Boolean attivo) { this.attivo = attivo; }

    public LocalDateTime getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(LocalDateTime dataCreazione) { this.dataCreazione = dataCreazione; }

    public LocalDateTime getDataAggiornamento() { return dataAggiornamento; }
    public void setDataAggiornamento(LocalDateTime dataAggiornamento) { this.dataAggiornamento = dataAggiornamento; }

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
        return "TisCorrieri{id=%d, nome='%s', email='%s', attivo=%s}"
            .formatted(id, nome, email, attivo);
    }
}