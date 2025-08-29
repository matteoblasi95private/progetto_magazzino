package it.personalproject.magazzini.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.magazzini.domain.MagazzinoModel;
import it.personalproject.magazzini.entities.TisMagazzini;

@Service
public class MagazziniModelToMagazziniEntityConverter implements Converter<MagazzinoModel,TisMagazzini> {

	@Override
	public TisMagazzini convert(MagazzinoModel source) {
		// TODO Auto-generated method stub
		return null;
	}

}
