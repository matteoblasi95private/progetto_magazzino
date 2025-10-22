package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.ordini.entities.TfStatoOrdine;

public interface StatoOrdineRepository extends JpaRepository<TfStatoOrdine, Integer> {

}
