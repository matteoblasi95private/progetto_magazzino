package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.ordini.entities.TisOrdini;

@Repository
public interface OrdiniRepository extends JpaRepository<TisOrdini, Integer> {

}
