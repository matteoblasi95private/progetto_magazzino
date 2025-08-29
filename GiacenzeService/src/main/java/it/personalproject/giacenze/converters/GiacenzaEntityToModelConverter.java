package it.personalproject.giacenze.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.giacenze.domain.GiacenzeModel;
import it.personalproject.giacenze.entities.TisGiacenze;

@Service
public class GiacenzaEntityToModelConverter implements Converter<TisGiacenze, GiacenzeModel> {
	
	@Autowired
	private MagazziniEntityToMagazziniModelConverter magazziniEntityToModelConverter;
	
	@Autowired
	private ProdottiEntityToProdottiModelConverter prodottiEntityToProdottiModelConverter;

	@Override
	public GiacenzeModel convert(TisGiacenze source) {
		
		GiacenzeModel result = null;
		
		if(source != null) {
			
			result = new GiacenzeModel();
			
			if(source.getProdotto() != null) {
				result.setProdotto(prodottiEntityToProdottiModelConverter.convert(source.getProdotto()));
			}
			
			if(source.getMagazzino() != null) {
				result.setMagazzino(magazziniEntityToModelConverter.convert(source.getMagazzino()));
			}
			
			result.setQuantitaDisponibile(source.getQuantitaDisponibile());
			
			result.setDataAggiornamento(source.getDataAggiornamento());
			
		}
		
		return result;
		
	}

}
