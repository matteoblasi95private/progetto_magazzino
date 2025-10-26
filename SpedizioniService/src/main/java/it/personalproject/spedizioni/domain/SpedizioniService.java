package it.personalproject.spedizioni.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.personalproject.ordini.domain.exceptions.CorrieriAttiviLiberiNotFoundException;
import it.personalproject.spedizioni.entities.TisSpedizioni;
import it.personalproject.spedizioni.exceptions.SpedizioneNotFoundException;

public interface SpedizioniService {
	
	public SpedizioneModel creaSpedizione(SpedizioneModel spedizione);
	
	public Optional<SpedizioneModel> getSpedizione(Integer id);
	
	public void cancellaSpedizione(Integer id) throws SpedizioneNotFoundException;
	
	public SpedizioneModel aggiornaSpedizione(SpedizioneModel spedizione) throws SpedizioneNotFoundException;
	
	public Collection<SpedizioneModel> getAllSpedizioni();
	
	public void scriviStoricoSpedizione(TisSpedizioni spedizione, String note);

	public String getStatoSpedizione(Integer id) throws SpedizioneNotFoundException;

	public Collection<StoricoSpedizioniModel> getStoricoSpedizione(Integer id);

	public SpedizioneModel creaSpedizioneFromOrdine(OrdineModel o) throws CorrieriAttiviLiberiNotFoundException;

}
