package it.personalproject.ordini.domain.ports;

import java.util.Collection;

import it.personalproject.ordini.domain.MagazzinoModel;

public interface GiacenzePort {
	
	public Collection<MagazzinoModel> getMagazziniConDisponibilitaProdotto(Integer idProdotto, Integer quantita);

}
