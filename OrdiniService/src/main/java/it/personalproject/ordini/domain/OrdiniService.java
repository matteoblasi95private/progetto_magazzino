package it.personalproject.ordini.domain;

public interface OrdiniService {
	
	public void creaOrdine(OrdineModel ordine);
	
	public OrdineModel getOrdine(Integer id);
	
	public void cancellaOrdine(Integer id);
	
	public void aggiornaOrdine(OrdineModel ordine);

}
