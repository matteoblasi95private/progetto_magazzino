package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.spedizioni.entities.TisSpedizioni;

@Repository
public interface SpedizioniRepository extends JpaRepository<TisSpedizioni, Integer> {

}
