package it.personalproject.magazzini.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.personalproject.magazzini.entities.TisMagazzini;
import it.personalproject.magazzini.exceptions.MagazzinoNotFoundException;

public interface MagazzinoService {
	
	public MagazzinoModel creaMagazzino(MagazzinoModel magazzino);
	
	public Optional<MagazzinoModel> getMagazzino(Integer id);
	
	public void cancellaMagazzino(Integer id);
	
	public MagazzinoModel aggiornaMagazzino(MagazzinoModel magazzino) throws MagazzinoNotFoundException;
	
	public List<MagazzinoModel> getAllMagazzini();
	
	public void scriviStoricoMagazzino(TisMagazzini magazzino, String note);

	public Collection<MagazzinoModel> getMagazziniCitta(String citta);
	
	public Collection<MagazzinoModel> getMagazziniPaese(String paese);
	
	public Collection<MagazzinoModel> getMagazziniAttivi();
	
}
