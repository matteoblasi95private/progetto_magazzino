package it.personalproject.giacenze.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.giacenze.entities.TisProdotti;

@Repository
public interface ProdottiRepository extends JpaRepository<TisProdotti, Integer> {

}
