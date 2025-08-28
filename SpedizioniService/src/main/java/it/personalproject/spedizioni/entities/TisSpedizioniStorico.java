package it.personalproject.spedizioni.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TIS_SPEDIZIONI_STORICO")
public class TisSpedizioniStorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSpedizione", nullable = false)
    private TisSpedizioni spedizione;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdStato", nullable = false)
    private TisSpedizioneStato stato;

    @Column(name = "DataEvento", nullable = false)
    private LocalDateTime dataEvento = LocalDateTime.now();

    @Column(name = "Descrizione", length = 500)
    private String descrizione;

    public TisSpedizioniStorico() {}

    public TisSpedizioniStorico(TisSpedizioni spedizione, TisSpedizioneStato stato, String descrizione) {
        this.spedizione = spedizione;
        this.stato = stato;
        this.dataEvento = LocalDateTime.now();
        this.descrizione = descrizione;
    }

    // --- getter/setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TisSpedizioni getSpedizione() { return spedizione; }
    public void setSpedizione(TisSpedizioni spedizione) { this.spedizione = spedizione; }

    public TisSpedizioneStato getStato() { return stato; }
    public void setStato(TisSpedizioneStato stato) { this.stato = stato; }

    public LocalDateTime getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDateTime dataEvento) { this.dataEvento = dataEvento; }

    public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TisSpedizioniStorico that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TisSpedizioniStorico{id=%d, stato=%s, dataEvento=%s}"
            .formatted(id, stato != null ? stato.getCodice() : null, dataEvento);
    }
}