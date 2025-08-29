package it.personalproject.magazzini.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "TIS_PRODOTTI")
public class TisProdotti {

    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String codiceProdotto;

    @Column(length = 1000)
    private String descrizione;

    @Column(nullable = false)
    private BigDecimal prezzo;

    @Column(nullable = false)
    private Integer quantitaDisponibile;

    private String categoria;

    // --- Costruttori ---
    public TisProdotti() {}

    public TisProdotti(String nome, String descrizione, BigDecimal prezzo, int quantitaDisponibile, String categoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
        this.categoria = categoria;
    }

    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

    public Integer getQuantitaDisponibile() { return quantitaDisponibile; }
    public void setQuantitaDisponibile(Integer quantitaDisponibile) { this.quantitaDisponibile = quantitaDisponibile; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}