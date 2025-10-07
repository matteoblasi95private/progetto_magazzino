package it.personalproject.spedizioni.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SpedizioneModel {

	@NotNull
	@Positive
    private Integer id;

	@NotNull
	@Positive
    private Integer idOrdine;
	
	@NotNull
	@Positive
    private Integer idCliente;
	
	@NotNull
	@Positive
    private Integer idCorriere;
	
	@NotNull
	@Positive
    private Integer idStato;

	@NotNull
	@Positive
    private String trackingNumber;

	
	@NotNull
	@Positive
    private BigDecimal costoSpedizione;

	@NotNull
    private String destNome;
	
	@NotNull
    private String destIndirizzo;
	
	@NotNull
    private String destCap;
	
	@NotNull
    private String destCitta;
	
	@NotNull
    private String destProvincia;
	
	@NotNull
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

    public BigDecimal getCostoSpedizione() { return costoSpedizione; }
    public void setCostoSpedizione(BigDecimal costoSpedizione) { this.costoSpedizione = costoSpedizione; }

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