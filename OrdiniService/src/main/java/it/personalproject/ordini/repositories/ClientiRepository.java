package it.personalproject.ordini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.ordini.entities.TisClienti;

@Repository
public interface ClientiRepository extends JpaRepository<TisClienti, Integer> {

}
