package it.personalproject.prodotti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.personalproject.prodotti.entities.TisProdotti;

public interface ProdottiRepository extends JpaRepository<TisProdotti, Integer> {

}
