package it.personalproject.clienti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.clienti.entities.TisClienti;

public interface ClientiRepository extends JpaRepository<TisClienti, Integer> {

}
