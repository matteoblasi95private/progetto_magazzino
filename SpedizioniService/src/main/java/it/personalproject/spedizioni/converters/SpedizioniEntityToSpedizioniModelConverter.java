package it.personalproject.spedizioni.converters;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.spedizioni.domain.SpedizioneModel;
import it.personalproject.spedizioni.entities.TisSpedizioni;

@Service
public class SpedizioniEntityToSpedizioniModelConverter implements Converter<TisSpedizioni, SpedizioneModel>{

	@Override
	public SpedizioneModel convert(TisSpedizioni source) {
		
		SpedizioneModel result = null;
		
		if(source != null) {
			result = new SpedizioneModel();
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
			result.setIdOrdine(source.getOrdine().getId());
			result.setIdStato(source.getStato().getId());
			result.setTrackingNumber(source.getTrackingNumber());
			
		}
		
		return result;
		
	}

}
