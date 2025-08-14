package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.ordini.entities.TisProdotti;

@Repository
public interface ProdottiRepository extends JpaRepository<TisProdotti, Integer> {

}
