package it.personalproject.ordini.domain;

import java.util.Collection;
import java.util.List;

public interface OrdiniService {
	
	public CreaOrdineResponse creaOrdine(OrdineModel ordine);
	
	public OrdineModel getOrdine(Integer id);
	
	public void cancellaOrdine(Integer id);
	
	public OrdineModel aggiornaOrdine(OrdineModel ordine);
	
	public Collection<OrdineModel> getAllOrdini();

}
