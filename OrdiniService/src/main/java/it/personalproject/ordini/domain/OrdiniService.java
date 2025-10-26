package it.personalproject.ordini.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.personalproject.ordini.exception.OrdineNotFoundException;

public interface OrdiniService {
	
	public CreaOrdineResponse creaOrdine(OrdineModel ordine);
	
	public Optional<OrdineModel> getOrdine(Integer id);
	
	public boolean cancellaOrdine(Integer id);
	
	public OrdineModel aggiornaOrdine(OrdineModel ordine) throws OrdineNotFoundException;
	
	public Collection<OrdineModel> getAllOrdini();

}
