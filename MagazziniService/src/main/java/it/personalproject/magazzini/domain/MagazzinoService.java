package it.personalproject.magazzini.domain;

import java.util.Collection;
import java.util.List;

import it.personalproject.magazzini.entities.TisMagazzini;

public interface MagazzinoService {
	
	public MagazzinoModel creaMagazzino(MagazzinoModel magazzino);
	
	public MagazzinoModel getMagazzino(Integer id);
	
	public void cancellaMagazzino(Integer id);
	
	public MagazzinoModel aggiornaMagazzino(MagazzinoModel magazzino);
	
	public List<MagazzinoModel> getAllMagazzini();
	
	public void scriviStoricoMagazzino(TisMagazzini magazzino, String note);

	//public Collection<StoricoMagazzinoModel> getStoricoMovimentiMagazzino(Integer id);

	public Collection<MagazzinoModel> getMagazziniCitta(String citta);
	
	public Collection<MagazzinoModel> getMagazziniPaese(String paese);
	
	public Collection<MagazzinoModel> getMagazziniAttivi();
	
}
