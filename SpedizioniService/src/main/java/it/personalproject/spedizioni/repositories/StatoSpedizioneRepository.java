package it.personalproject.spedizioni.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.spedizioni.entities.TisSpedizioneStato;

@Repository
public interface StatoSpedizioneRepository extends JpaRepository<TisSpedizioneStato, Integer> {
	
	public Optional<TisSpedizioneStato> findByCodice(String codice);

}
