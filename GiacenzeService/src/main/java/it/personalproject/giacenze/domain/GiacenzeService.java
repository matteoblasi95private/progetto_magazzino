package it.personalproject.giacenze.domain;

import java.util.Collection;

public interface GiacenzeService {

	public GiacenzeModel creaStock(GiacenzeModel giacenza);

	public GiacenzeModel getDettaglioStock(Integer idProdotto, Integer idMagazzino);

	public GiacenzeModel aggiornaQuantita(GiacenzeModel giacenza);

	public Collection<GiacenzeModel> getListaStockProdotto(Integer idProdotto);

	public Collection<GiacenzeModel> getListaStockMagazzino(Integer idMagazzino);
	
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita);

	public GiacenzeModel trasferisciProdotto(TrasferimentoProdottoDTO trasferimentoDTO);
	
	public void cancellaGiacenza(GiacenzeModel giacenza);
		
}
