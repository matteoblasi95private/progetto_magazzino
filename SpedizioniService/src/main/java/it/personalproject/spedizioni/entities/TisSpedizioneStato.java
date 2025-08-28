package it.personalproject.spedizioni.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(
    name = "TIS_SPEDIZIONE_STATO",
    uniqueConstraints = {
        @UniqueConstraint(name = "UQ_TIS_SPEDIZIONE_STATO_Codice", columnNames = "Codice")
    }
)
public class TisSpedizioneStato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Codice", nullable = false, length = 30)
    private String codice;   // es. CREATA, RITIRATA, IN_TRANSITO, CONSEGNATA...

    @Column(name = "Descr", nullable = false, length = 150)
    private String descr;

    public TisSpedizioneStato() {}

    public TisSpedizioneStato(String codice, String descr) {
        this.codice = codice;
        this.descr = descr;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodice() { return codice; }
    public void setCodice(String codice) { this.codice = codice; }

    public String getDescr() { return descr; }
    public void setDescr(String descr) { this.descr = descr; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TisSpedizioneStato that)) return false;
        return (id != null && id.equals(that.id)) ||
               (id == null && that.id == null && Objects.equals(codice, that.codice));
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : Objects.hashCode(codice);
    }

    @Override
    public String toString() {
        return "TisSpedizioneStato{id=%d, codice='%s', descr='%s'}"
            .formatted(id, codice, descr);
    }
}