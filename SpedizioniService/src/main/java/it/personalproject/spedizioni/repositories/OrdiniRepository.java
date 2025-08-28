package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.spedizioni.entities.TisOrdini;

@Repository
public interface OrdiniRepository extends JpaRepository<TisOrdini, Integer> {

}
