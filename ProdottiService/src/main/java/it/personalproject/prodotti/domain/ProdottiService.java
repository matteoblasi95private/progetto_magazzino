package it.personalproject.prodotti.domain;

import java.util.List;
import java.util.Optional;

public interface ProdottiService {
	
	public ProdottiModel creaProdotto(ProdottiModel ordine);
	
	public Optional<ProdottiModel> getProdotto(Integer id);
	
	public void cancellaProdotto(Integer id);
	
	public ProdottiModel aggiornaProdotto(ProdottiModel ordine);
	
	public List<ProdottiModel> getAllProdotti();

}
