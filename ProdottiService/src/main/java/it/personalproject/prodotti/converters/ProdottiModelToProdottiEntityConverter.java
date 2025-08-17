package it.personalproject.prodotti.converters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.prodotti.domain.ProdottiModel;
import it.personalproject.prodotti.entities.TisProdotti;
import it.personalproject.prodotti.repositories.ProdottiRepository;

@Service
public class ProdottiModelToProdottiEntityConverter implements Converter<ProdottiModel, TisProdotti> {

	@Override
	public TisProdotti convert(ProdottiModel source) {
		
		TisProdotti result = null;
		
		if(source != null) {
			
			result = new TisProdotti();
			
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
