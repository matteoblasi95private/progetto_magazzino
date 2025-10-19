package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.ordini.entities.TisClienti;

public interface ClientiRepository extends JpaRepository<TisClienti, Integer> {

}
