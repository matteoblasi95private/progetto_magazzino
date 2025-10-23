package it.personalproject.spedizioni.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.spedizioni.entities.TfStatoCorriere;
import it.personalproject.spedizioni.entities.TisCorrieri;

public interface CorrieriRepository extends JpaRepository<TisCorrieri, Integer> {
	
	public Collection<TisCorrieri> findByIdStatoCorriereAndAttivo(TfStatoCorriere idStatoCorriere, boolean attivo);

}
