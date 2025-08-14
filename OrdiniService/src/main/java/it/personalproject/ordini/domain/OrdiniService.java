package it.personalproject.ordini.domain;

import java.util.List;

public interface OrdiniService {
	
	public OrdineModel creaOrdine(OrdineModel ordine);
	
	public OrdineModel getOrdine(Integer id);
	
	public void cancellaOrdine(Integer id);
	
	public OrdineModel aggiornaOrdine(OrdineModel ordine);
	
	public List<OrdineModel> getAllOrdini();

}
