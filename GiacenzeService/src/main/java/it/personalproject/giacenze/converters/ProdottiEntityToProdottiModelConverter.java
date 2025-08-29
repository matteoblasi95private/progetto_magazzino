package it.personalproject.giacenze.converters;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.giacenze.domain.ProdottiModel;
import it.personalproject.giacenze.entities.TisProdotti;

@Service
public class ProdottiEntityToProdottiModelConverter implements Converter<TisProdotti, ProdottiModel>{

	@Override
	public ProdottiModel convert(TisProdotti source) {
		
		ProdottiModel result = null;
		
		if(source != null) {
			
			result = new ProdottiModel();
			
			result.setCategoria(source.getCategoria());
			result.setCodiceProdotto(source.getCodiceProdotto());
			result.setDataAggiornamento(source.getDataAggiornamento());
			result.setDataCreazione(source.getDataCreazione());
			result.setDescrizione(source.getDescrizione());
			result.setId(source.getId());
			result.setNome(source.getNome());
			result.setPrezzo(source.getPrezzo());
			result.setQuantitaDisponibile(source.getQuantitaDisponibile());
			
		}
		
		return result;
		
	}

}
