package it.personalproject.magazzini.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.personalproject.magazzini.entities.TisMagazzinoStoricoMovimenti;

@Repository
public interface StoricoMagazziniRepository extends JpaRepository<TisMagazzinoStoricoMovimenti, Integer> {
	
	@Query(value = "SELECT s FROM TisMagazzinoStoricoMovimenti s INNER JOIN FETCH s.magazzino m where m.id = :idMagazzino")
	public Collection<TisMagazzinoStoricoMovimenti> getStoricoMagazzino(@Param("idMagazzino") Integer idMagazzino);

}
