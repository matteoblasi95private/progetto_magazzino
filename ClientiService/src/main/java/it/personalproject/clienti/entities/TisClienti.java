package it.personalproject.clienti.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "TIS_CLIENTI", uniqueConstraints = @UniqueConstraint(name = "CLIENTI_UNIQUE_CF", columnNames = "CodiceFiscale"))
public class TisClienti {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CodiceFiscale", nullable = false)
    private String codiceFiscale;
    
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
    
    @Column(name = "Data_registrazione", updatable = false)
    private LocalDateTime dataRegistrazione;
    
    @Column(name = "Attivo", nullable = false)
    private Boolean attivo;

    public TisClienti() {}

    public TisClienti(String codiceFiscale, String nome, String cognome, String email, String telefono, String indirizzo, String citta, String cap, String paese) {
        this.codiceFiscale = codiceFiscale;
    	this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.paese = paese;
    }
    
    @Override
    public boolean equals(Object o) {
    	
    	if(o == null) return false;
    	if(this == o) return true;
    	
    	if(!getClass().equals(o.getClass()))
    		return false;
    	
    	TisClienti cliente = (TisClienti) o;
    	
    	return Objects.equals(this.id, cliente.getId());
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(id);
    }

    // --- Getter & Setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

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

	public LocalDateTime getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(LocalDateTime dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
    
    
    
    
}