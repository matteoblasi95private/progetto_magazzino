package it.personalproject.spedizioni.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.spedizioni.domain.StoricoSpedizioniModel;
import it.personalproject.spedizioni.entities.TisSpedizioniStorico;
import it.personalproject.spedizioni.repositories.OrdiniRepository;
import it.personalproject.spedizioni.repositories.StatoSpedizioneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StoricoSpedizioniEntityToModelConverter implements Converter<TisSpedizioniStorico, StoricoSpedizioniModel> {

	@Override
	public StoricoSpedizioniModel convert(TisSpedizioniStorico source) {
		
		StoricoSpedizioniModel result = null;
		
		if(source != null) {
			
			result.setId(source.getId());
			result.setDescrizione(source.getDescrizione());
			result.setDataEvento(source.getDataEvento());
			result.setIdStato(source.getStato().getId());
			result.setIdSpedizione(source.getSpedizione().getId());
			
		}
		
		return result;
		
	}

}
