package it.personalproject.prodotti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.prodotti.entities.TisProdotti;

@Repository
public interface ProdottiRepository extends JpaRepository<TisProdotti, Integer> {

}
