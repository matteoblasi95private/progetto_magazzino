package it.personalproject.clienti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.clienti.entities.TisClienti;

@Repository
public interface ClientiRepository extends JpaRepository<TisClienti, Integer> {

}
