package it.personalproject.magazzini.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.personalproject.magazzini.entities.TisMagazzini;

@Repository
public interface MagazziniRepository extends JpaRepository<TisMagazzini, Integer> {
	
	
	@Query(value = "SELECT m FROM TisMagazzini m where m.citta = :citta")
	public Collection<TisMagazzini> getMagazziniCitta(@Param("citta") String citta);
	
	@Query(value = "SELECT m FROM TisMagazzini m where m.paese = :paese")
	public Collection<TisMagazzini> getMagazziniPaese(@Param("paese") String citta);
	
	public Collection<TisMagazzini> findByAttivo(boolean attivo);

}
