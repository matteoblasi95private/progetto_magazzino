package it.personalproject.ordini.converters;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.entities.TisOrdini;

@Service
public class OrdiniEntityToOrdiniModelConverter implements Converter<TisOrdini, OrdineModel>{

	@Override
	public OrdineModel convert(TisOrdini source) {
		
		OrdineModel result = null;
		
		if(source != null) {
			
			result = new OrdineModel();
			
			result.setId(source.getId());
			
			if(source.getIdCliente() != null) {
				result.setIdCliente(source.getIdCliente().getId());
			}
			
			if(source.getIdProdotto() != null) {
				result.setIdProdotto(source.getIdProdotto().getId());
			}
			
			result.setDataCreazione(source.getDataCreazione());
			result.setDataAggiornamento(source.getDataAggiornamento());
			
			
		}
		
		return result;
		
	}

}
