package it.personalproject.giacenze.domain;

import java.util.Collection;
import java.util.Optional;

import it.personalproject.giacenze.exceptions.StockNotFoundException;

public interface GiacenzeService {

	public GiacenzeModel creaStock(GiacenzeModel giacenza);

	public Optional<GiacenzeModel> getDettaglioStock(Integer idProdotto, Integer idMagazzino);

	public GiacenzeModel aggiornaQuantita(GiacenzeModel giacenza) throws StockNotFoundException;

	public Collection<GiacenzeModel> getListaStockProdotto(Integer idProdotto);

	public Collection<GiacenzeModel> getListaStockMagazzino(Integer idMagazzino);
	
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita);

	public GiacenzeModel trasferisciProdotto(TrasferimentoProdottoDTO trasferimentoDTO) throws StockNotFoundException;
	
	public void cancellaGiacenza(GiacenzeModel giacenza) throws StockNotFoundException;
		
}
