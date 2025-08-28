package it.personalproject.spedizioni.domain;

import java.util.Collection;
import java.util.List;

import it.personalproject.spedizioni.entities.TisSpedizioni;

public interface SpedizioniService {
	
	public SpedizioneModel creaSpedizione(SpedizioneModel spedizione);
	
	public SpedizioneModel getSpedizione(Integer id);
	
	public void cancellaSpedizione(Integer id);
	
	public SpedizioneModel aggiornaSpedizione(SpedizioneModel spedizione);
	
	public List<SpedizioneModel> getAllSpedizioni();
	
	public void scriviStoricoSpedizione(TisSpedizioni spedizione, String note);

	public String getStatoSpedizione(Integer id);

	public Collection<StoricoSpedizioniModel> getStoricoSpedizione(Integer id);

}
