package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.personalproject.spedizioni.entities.TisSpedizioni;

public interface SpedizioniRepository extends JpaRepository<TisSpedizioni, Integer> {

}
