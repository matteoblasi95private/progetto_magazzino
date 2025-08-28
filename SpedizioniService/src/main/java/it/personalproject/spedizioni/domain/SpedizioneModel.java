package it.personalproject.spedizioni.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SpedizioneModel {

    private Integer id;

    private Integer idOrdine;
    private Integer idCliente;
    private Integer idCorriere;
    private Integer idStato;

    private String trackingNumber;
    private String servizio;

    private BigDecimal costoSpedizione;
    private BigDecimal assicurazione;

    private BigDecimal pesoKg;
    private BigDecimal lunghezzaCm;
    private BigDecimal larghezzaCm;
    private BigDecimal altezzaCm;

    private String destNome;
    private String destIndirizzo;
    private String destCap;
    private String destCitta;
    private String destProvincia;
    private String destPaese;

    private LocalDateTime dataCreazione;
    private LocalDateTime dataAggiornamento;
    private LocalDateTime dataRitiro;
    private LocalDateTime dataConsegnaPrevista;
    private LocalDateTime dataConsegnaEffettiva;

    // --- getters/setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdOrdine() { return idOrdine; }
    public void setIdOrdine(Integer idOrdine) { this.idOrdine = idOrdine; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public Integer getIdCorriere() { return idCorriere; }
    public void setIdCorriere(Integer idCorriere) { this.idCorriere = idCorriere; }

    public Integer getIdStato() { return idStato; }
    public void setIdStato(Integer idStato) { this.idStato = idStato; }

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