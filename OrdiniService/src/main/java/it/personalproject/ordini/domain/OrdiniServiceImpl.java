package it.personalproject.ordini.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.personalproject.ordini.converters.OrdiniModelToOrdiniEntityConverter;
import it.personalproject.ordini.converters.OrdiniEntityToOrdiniModelConverter;
import it.personalproject.ordini.entities.TisOrdini;
import it.personalproject.ordini.repositories.OrdiniRepository;

public class OrdiniServiceImpl implements OrdiniService {
	
	private final OrdiniRepository ordiniRepository;
	
	private final OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConverter;
	
	private final OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConverter;
	
	@Autowired
	public OrdiniServiceImpl(OrdiniRepository ordiniRepo, OrdiniModelToOrdiniEntityConverter ordiniModelToOrdiniEntityConv, OrdiniEntityToOrdiniModelConverter ordiniEntityToOrdiniModelConv) {
		this.ordiniRepository = ordiniRepo;
		this.ordiniModelToOrdiniEntityConverter = ordiniModelToOrdiniEntityConv;
		this.ordiniEntityToOrdiniModelConverter = ordiniEntityToOrdiniModelConv;
	}

	@Override
	public void creaOrdine(OrdineModel ordine) {
		
		ordiniRepository.save(ordiniModelToOrdiniEntityConverter.convert(ordine));
		
	}

	@Override
	public OrdineModel getOrdine(Integer id) {
		
		OrdineModel result = null;
	
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		
		if(ordineEntity.isPresent()) {
			result = ordiniEntityToOrdiniModelConverter.convert(ordineEntity.get());
		}
		
		return result;
		
	}

	@Override
	public void cancellaOrdine(Integer id) {
		Optional<TisOrdini> ordineEntity = ordiniRepository.findById(id);
		if(ordineEntity.isPresent()) {
			ordiniRepository.delete(ordineEntity.get());
		}
	}

	@Override
	public void aggiornaOrdine(OrdineModel ordine) {
		
		
		
	}

}
