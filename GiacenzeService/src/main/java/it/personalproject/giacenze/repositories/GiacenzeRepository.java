package it.personalproject.giacenze.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.personalproject.giacenze.entities.TisGiacenze;
import it.personalproject.giacenze.entities.TisGiacenzePK;
import it.personalproject.giacenze.entities.TisMagazzini;

@Repository
public interface GiacenzeRepository extends JpaRepository<TisGiacenze, TisGiacenzePK> {
	
	
	@Query(value = "SELECT g FROM TisGiacenze g where g.giacenzePK.idMagazzino = :idMagazzino")
	public Collection<TisGiacenze> getListaStockMagazzino(@Param("idMagazzino") Integer idMagazzino);
	
	
	@Query(value = "SELECT g FROM TisGiacenze g where g.giacenzePK.idProdotto = :idProdotto")
	public Collection<TisGiacenze> getListaStockProdotto(@Param("idProdotto") Integer idProdotto);
	
	
	@Query(value = "SELECT g FROM TisGiacenze g inner join g.magazzino m p where g.giacenzePK.idProdotto = = :idProdotto and g.quantitaDisponibile > 0")
	public Collection<TisMagazzini> getListaMagazziniDisponibilitaProdotto(@Param("idProdotto") Integer idProdotto);

}
