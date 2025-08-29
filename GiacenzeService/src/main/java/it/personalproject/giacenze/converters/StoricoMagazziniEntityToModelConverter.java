package it.personalproject.giacenze.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.giacenze.domain.StoricoMagazzinoModel;
import it.personalproject.giacenze.entities.TisMagazzinoStoricoMovimenti;

@Service
public class StoricoMagazziniEntityToModelConverter implements Converter<TisMagazzinoStoricoMovimenti, StoricoMagazzinoModel> {

	@Autowired
	private MagazziniEntityToMagazziniModelConverter magazziniEntityToMagazziniModelConverter;
	
	@Autowired
	private ProdottiEntityToProdottiModelConverter prodottiEntityToProdottiModelConverter;
	
	@Override
	public StoricoMagazzinoModel convert(TisMagazzinoStoricoMovimenti source) {
		
		StoricoMagazzinoModel result = null;
		
		if(source != null) {
			result = new StoricoMagazzinoModel();
			result.setId(source.getId());
			result.setDataMovimento(source.getDataMovimento());
			if(source.getMagazzino() != null) {
				result.setMagazzino(magazziniEntityToMagazziniModelConverter.convert(source.getMagazzino()));
			}
			if(source.getProdotto() != null) {
				result.setProdotto(prodottiEntityToProdottiModelConverter.convert(source.getProdotto()));
			}
			result.setNote(source.getNote());
			result.setQuantitaMovimento(source.getQuantitaMovimento());
			result.setRiferimento(source.getRiferimento());
			result.setTipoMovimento(source.getTipoMovimento());
			result.setUtente(source.getUtente());
		}
		
		return result;
		
	}
	
	

}
