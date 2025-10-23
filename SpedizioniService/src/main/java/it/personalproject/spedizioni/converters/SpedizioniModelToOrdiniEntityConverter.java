package it.personalproject.spedizioni.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.spedizioni.domain.SpedizioneModel;
import it.personalproject.spedizioni.entities.TisSpedizioni;
import it.personalproject.spedizioni.repositories.CorrieriRepository;
import it.personalproject.spedizioni.repositories.OrdiniRepository;
import it.personalproject.spedizioni.repositories.StatoSpedizioneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SpedizioniModelToOrdiniEntityConverter implements Converter<SpedizioneModel, TisSpedizioni>{
	
	@Autowired
	private StatoSpedizioneRepository statoSpedizioneRepository;
	
	@Autowired
	private OrdiniRepository ordiniRepository;
	
	@Autowired
	private CorrieriRepository corrieriRepository;

	@Override
	public TisSpedizioni convert(SpedizioneModel source) {
		
		TisSpedizioni result = null;
		
		if(source != null) {
			
			result = new TisSpedizioni();
			result.setCostoSpedizione(source.getCostoSpedizione());
			result.setDataAggiornamento(source.getDataAggiornamento());
			result.setDataConsegnaEffettiva(source.getDataConsegnaEffettiva());
			result.setDataConsegnaPrevista(source.getDataConsegnaPrevista());
			result.setDataCreazione(source.getDataCreazione());
			result.setDataRitiro(source.getDataRitiro());
			result.setDestCap(source.getDestCap());
			result.setDestCitta(source.getDestCitta());
			result.setDestIndirizzo(source.getDestIndirizzo());
			result.setDestNome(source.getDestNome());
			result.setDestPaese(source.getDestPaese());
			result.setDestProvincia(source.getDestProvincia());
			result.setTrackingNumber(source.getTrackingNumber());
			
			
			if(source.getIdOrdine() != null) {
				result.setOrdine(ordiniRepository.findById(source.getIdOrdine()).orElseThrow(() -> new EntityNotFoundException("ORDINE NON TROVATO ASSOCIATO A ID " + source.getIdOrdine())));
			}
			
			if(source.getIdStato() != null) {
				result.setStato(statoSpedizioneRepository.findById(source.getIdStato()).orElseThrow(() -> new EntityNotFoundException("STATO NON TROVATO ASSOCIATO A ID " + source.getIdStato())));
			}
			
			if(source.getIdCorriere() != null) {
				result.setCorriere(corrieriRepository.findById(source.getIdCorriere()).orElseThrow(() -> new EntityNotFoundException("CORRIERE NON TROVATO ASSOCIATO A ID " + source.getIdCorriere())));
			}
			
			
		}
		
		return result;
		
	}

}
