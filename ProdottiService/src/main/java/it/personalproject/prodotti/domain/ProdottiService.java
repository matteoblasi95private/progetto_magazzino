package it.personalproject.prodotti.domain;

import java.util.List;

public interface ProdottiService {
	
	public ProdottiModel creaProdotto(ProdottiModel ordine);
	
	public ProdottiModel getProdotto(Integer id);
	
	public void cancellaProdotto(Integer id);
	
	public ProdottiModel aggiornaProdotto(ProdottiModel ordine);
	
	public List<ProdottiModel> getAllProdotti();

}
