package it.personalproject.storico;

import java.util.Collection;

import it.personalproject.giacenze.domain.MagazzinoModel;
import it.personalproject.giacenze.domain.ProdottiModel;
import it.personalproject.giacenze.domain.StoricoMagazzinoModel;

public interface StoricoService {
	
	public void scriviStoricoMovimenti(MagazzinoModel magazzino, ProdottiModel prodotto, Integer quantitaMovimento, String tipoMovimento, String riferimento, String utente, String note);
	
	public Collection<StoricoMagazzinoModel> getStoricoMovimentiProdotto(Integer idProdotto);
	
	public Collection<StoricoMagazzinoModel> getStoricoMovimentiMagazzino(Integer idMagazzino);

}
