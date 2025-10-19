package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.ordini.entities.TisOrdini;

public interface OrdiniRepository extends JpaRepository<TisOrdini, Integer> {

}
