package it.personalproject.ordini.converters;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import it.personalproject.ordini.domain.OrdineModel;
import it.personalproject.ordini.entities.TisClienti;
import it.personalproject.ordini.entities.TisOrdini;
import it.personalproject.ordini.entities.TisProdotti;
import it.personalproject.ordini.repositories.ClientiRepository;
import it.personalproject.ordini.repositories.ProdottiRepository;

@Service
public class OrdiniModelToOrdiniEntityConverter implements Converter<OrdineModel, TisOrdini>{
	
	@Autowired
	private ClientiRepository clientiRepository;
	
	@Autowired
	private ProdottiRepository prodottiRepository;

	@Override
	public @Nullable TisOrdini convert(OrdineModel source) {
		
		TisOrdini result = null;
		
		if(source != null) {
			
			result = new TisOrdini();
			Optional<TisClienti> cliente = clientiRepository.findById(source.getIdCliente());
			if(cliente.isPresent()) {
				result.setIdCliente(cliente.get());
			}
			
			Optional<TisProdotti> prodotto = prodottiRepository.findById(source.getIdProdotto());
			if(prodotto.isPresent()) {
				result.setIdProdotto(prodotto.get());
			}
			
			
		}
		
		return result;
		
	}

}
