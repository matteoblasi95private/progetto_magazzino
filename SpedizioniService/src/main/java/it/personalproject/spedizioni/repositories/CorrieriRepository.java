package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.spedizioni.entities.TisCorrieri;

@Repository
public interface CorrieriRepository extends JpaRepository<TisCorrieri, Integer> {

}
