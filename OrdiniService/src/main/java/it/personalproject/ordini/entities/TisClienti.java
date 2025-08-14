package it.personalproject.ordini.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TIS_CLIENTI")
public class TisClienti {

    @Id
    private Integer id;

    @Column(name = "CodiceFiscale", nullable = false)
    private String codiceFisale;
    
    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Cognome", nullable = false)
    private String cognome;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Indirizzo")
    private String indirizzo;

    @Column(name = "Citta")
    private String citta;
    
    @Column(name = "Cap")
    private String cap;
    
    @Column(name = "Paese")
    private String paese;

    // --- Costruttori ---
    public TisClienti() {}

    public TisClienti(String nome, String cognome, String email, String telefono, String indirizzo, String citta, String cap, String paese) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.paese = paese;
    }

    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getPaese() { return paese; }
    public void setPaese(String paese) { this.paese = paese; }
}