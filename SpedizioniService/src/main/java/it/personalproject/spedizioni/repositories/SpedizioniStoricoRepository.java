package it.personalproject.spedizioni.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.personalproject.spedizioni.entities.TisSpedizioniStorico;

public interface SpedizioniStoricoRepository extends JpaRepository<TisSpedizioniStorico, Integer> {
	
	@Query(value = "SELECT storico FROM TisSpedizioniStorico storico INNER JOIN FETCH storico.spedizione sped where sped.id = :idSpedizione")
	public Collection<TisSpedizioniStorico> getStoricoSpedizione(@Param("idSpedizione") Integer idSpedizione);

}
