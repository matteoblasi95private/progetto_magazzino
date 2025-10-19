package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.personalproject.ordini.entities.TisProdotti;

public interface ProdottiRepository extends JpaRepository<TisProdotti, Integer> {

}
