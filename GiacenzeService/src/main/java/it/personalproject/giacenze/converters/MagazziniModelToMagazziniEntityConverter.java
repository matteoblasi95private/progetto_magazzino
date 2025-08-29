package it.personalproject.giacenze.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.giacenze.domain.MagazzinoModel;
import it.personalproject.giacenze.entities.TisMagazzini;
@Service
public class MagazziniModelToMagazziniEntityConverter implements Converter<MagazzinoModel,TisMagazzini> {

	@Override
	public TisMagazzini convert(MagazzinoModel source) {
		
		TisMagazzini result = null;
		
		if(source != null) {
			result = new TisMagazzini();
			result.setAttivo(source.getAttivo());
			result.setCitta(source.getCitta());
			result.setCodice(source.getCodice());
			result.setDataCreazione(source.getDataCreazione());
			result.setId(source.getId());
			result.setIndirizzo(source.getIndirizzo());
			result.setNome(source.getNome());
			result.setPaese(source.getPaese());
		}
		
		return result;
		
	}

}
