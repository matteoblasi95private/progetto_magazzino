package it.personalproject.magazzini.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.magazzini.domain.MagazzinoModel;
import it.personalproject.magazzini.entities.TisMagazzini;

@Service
public class MagazziniEntityToMagazziniModelConverter implements Converter<TisMagazzini, MagazzinoModel>{

	@Override
	public MagazzinoModel convert(TisMagazzini source) {
		
		MagazzinoModel result = null;
		
		if(source != null) {
			result = new MagazzinoModel();
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
