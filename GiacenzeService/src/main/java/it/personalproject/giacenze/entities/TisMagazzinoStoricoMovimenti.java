package it.personalproject.giacenze.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TIS_MAGAZZINO_STORICO_MOVIMENTI")
public class TisMagazzinoStoricoMovimenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMagazzino", nullable = false)
    private TisMagazzini magazzino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProdotto", nullable = false)
    private TisProdotti prodotto;

    @Column(name = "QuantitaMovimento", nullable = false)
    private Integer quantitaMovimento;

    @Column(name = "TipoMovimento", nullable = false)
    private String tipoMovimento;

    @Column(name = "Riferimento")
    private String riferimento;

    @Column(name = "DataMovimento", nullable = false)
    private LocalDateTime dataMovimento;

    @Column(name = "Utente", nullable = false)
    private String utente;

    @Column(name = "Note")
    private String note;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TisMagazzini getMagazzino() {
		return magazzino;
	}
	public void setMagazzino(TisMagazzini magazzino) {
		this.magazzino = magazzino;
	}
	public TisProdotti getProdotto() {
		return prodotto;
	}
	public void setProdotto(TisProdotti prodotto) {
		this.prodotto = prodotto;
	}
	public Integer getQuantitaMovimento() {
		return quantitaMovimento;
	}
	public void setQuantitaMovimento(Integer quantitaMovimento) {
		this.quantitaMovimento = quantitaMovimento;
	}
	public String getTipoMovimento() { return tipoMovimento; }
    public void setTipoMovimento(String tipoMovimento) { this.tipoMovimento = tipoMovimento; }

    public String getRiferimento() { return riferimento; }
    public void setRiferimento(String riferimento) { this.riferimento = riferimento; }

    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    public String getUtente() { return utente; }
    public void setUtente(String utente) { this.utente = utente; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    
}