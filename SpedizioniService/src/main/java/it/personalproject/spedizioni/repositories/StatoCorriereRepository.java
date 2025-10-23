package it.personalproject.spedizioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.spedizioni.entities.TfStatoCorriere;

public interface StatoCorriereRepository extends JpaRepository<TfStatoCorriere, Integer> {

}
