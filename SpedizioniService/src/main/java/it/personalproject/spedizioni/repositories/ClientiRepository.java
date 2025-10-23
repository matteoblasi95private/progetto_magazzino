package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.spedizioni.entities.TisClienti;

public interface ClientiRepository extends JpaRepository<TisClienti, Integer> {

}
