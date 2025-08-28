package it.personalproject.spedizioni.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "TIS_SPEDIZIONI")
public class TisSpedizioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    // FK
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdOrdine", nullable = false)
    private TisOrdini ordine;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCorriere", nullable = false)
    private TisCorrieri corriere;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IdStato", nullable = false)
    private TisSpedizioneStato stato;

    // Dati spedizione
    @Column(name = "TrackingNumber", nullable = false, unique = true, length = 80)
    private String trackingNumber;

    @Column(name = "Servizio", length = 60)
    private String servizio;

    @Column(name = "CostoSpedizione", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoSpedizione = BigDecimal.ZERO;

    @Column(name = "Assicurazione", precision = 10, scale = 2)
    private BigDecimal assicurazione;

    @Column(name = "PesoKg", precision = 10, scale = 3)
    private BigDecimal pesoKg;

    @Column(name = "LunghezzaCm", precision = 10, scale = 2)
    private BigDecimal lunghezzaCm;

    @Column(name = "LarghezzaCm", precision = 10, scale = 2)
    private BigDecimal larghezzaCm;

    @Column(name = "AltezzaCm", precision = 10, scale = 2)
    private BigDecimal altezzaCm;

    // Indirizzo di destinazione (snapshot)
    @Column(name = "DestNome", nullable = false, length = 150)
    private String destNome;

    @Column(name = "DestIndirizzo", nullable = false, length = 255)
    private String destIndirizzo;

    @Column(name = "DestCap", nullable = false, length = 10)
    private String destCap;

    @Column(name = "DestCitta", nullable = false, length = 100)
    private String destCitta;

    @Column(name = "DestProvincia", length = 50)
    private String destProvincia;

    @Column(name = "DestPaese", nullable = false, length = 100)
    private String destPaese;

    // Date
    @Column(name = "DataCreazione", nullable = false, updatable = false)
    private LocalDateTime dataCreazione;

    @Column(name = "DataAggiornamento", nullable = false)
    private LocalDateTime dataAggiornamento;

    @Column(name = "DataRitiro")
    private LocalDateTime dataRitiro;

    @Column(name = "DataConsegnaPrevista")
    private LocalDateTime dataConsegnaPrevista;

    @Column(name = "DataConsegnaEffettiva")
    private LocalDateTime dataConsegnaEffettiva;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.dataCreazione = now;
        this.dataAggiornamento = now;
    }

    @PreUpdate
    public void onUpdate() {
        this.dataAggiornamento = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TisOrdini getOrdine() { return ordine; }
    public void setOrdine(TisOrdini ordine) { this.ordine = ordine; }

    public TisCorrieri getCorriere() { return corriere; }
    public void setCorriere(TisCorrieri corriere) { this.corriere = corriere; }

    public TisSpedizioneStato getStato() { return stato; }
    public void setStato(TisSpedizioneStato stato) { this.stato = stato; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getServizio() { return servizio; }
    public void setServizio(String servizio) { this.servizio = servizio; }

    public BigDecimal getCostoSpedizione() { return costoSpedizione; }
    public void setCostoSpedizione(BigDecimal costoSpedizione) { this.costoSpedizione = costoSpedizione; }

    public BigDecimal getAssicurazione() { return assicurazione; }
    public void setAssicurazione(BigDecimal assicurazione) { this.assicurazione = assicurazione; }

    public BigDecimal getPesoKg() { return pesoKg; }
    public void setPesoKg(BigDecimal pesoKg) { this.pesoKg = pesoKg; }

    public BigDecimal getLunghezzaCm() { return lunghezzaCm; }
    public void setLunghezzaCm(BigDecimal lunghezzaCm) { this.lunghezzaCm = lunghezzaCm; }

    public BigDecimal getLarghezzaCm() { return larghezzaCm; }
    public void setLarghezzaCm(BigDecimal larghezzaCm) { this.larghezzaCm = larghezzaCm; }

    public BigDecimal getAltezzaCm() { return altezzaCm; }
    public void setAltezzaCm(BigDecimal altezzaCm) { this.altezzaCm = altezzaCm; }

    public String getDestNome() { return destNome; }
    public void setDestNome(String destNome) { this.destNome = destNome; }

    public String getDestIndirizzo() { return destIndirizzo; }
    public void setDestIndirizzo(String destIndirizzo) { this.destIndirizzo = destIndirizzo; }

    public String getDestCap() { return destCap; }
    public void setDestCap(String destCap) { this.destCap = destCap; }

    public String getDestCitta() { return destCitta; }
    public void setDestCitta(String destCitta) { this.destCitta = destCitta; }

    public String getDestProvincia() { return destProvincia; }
    public void setDestProvincia(String destProvincia) { this.destProvincia = destProvincia; }

    public String getDestPaese() { return destPaese; }
    public void setDestPaese(String destPaese) { this.destPaese = destPaese; }

    public LocalDateTime getDataCreazione() { return dataCreazione; }
    public void setDataCreazione(LocalDateTime dataCreazione) { this.dataCreazione = dataCreazione; }

    public LocalDateTime getDataAggiornamento() { return dataAggiornamento; }
    public void setDataAggiornamento(LocalDateTime dataAggiornamento) { this.dataAggiornamento = dataAggiornamento; }

    public LocalDateTime getDataRitiro() { return dataRitiro; }
    public void setDataRitiro(LocalDateTime dataRitiro) { this.dataRitiro = dataRitiro; }

    public LocalDateTime getDataConsegnaPrevista() { return dataConsegnaPrevista; }
    public void setDataConsegnaPrevista(LocalDateTime dataConsegnaPrevista) { this.dataConsegnaPrevista = dataConsegnaPrevista; }

    public LocalDateTime getDataConsegnaEffettiva() { return dataConsegnaEffettiva; }
    public void setDataConsegnaEffettiva(LocalDateTime dataConsegnaEffettiva) { this.dataConsegnaEffettiva = dataConsegnaEffettiva; }
}