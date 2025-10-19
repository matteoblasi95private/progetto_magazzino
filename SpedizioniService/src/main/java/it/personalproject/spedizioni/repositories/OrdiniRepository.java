package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.personalproject.spedizioni.entities.TisOrdini;

public interface OrdiniRepository extends JpaRepository<TisOrdini, Integer> {

}
