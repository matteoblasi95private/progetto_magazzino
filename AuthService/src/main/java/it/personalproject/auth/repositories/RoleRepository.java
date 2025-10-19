package it.personalproject.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.auth.entities.TisRole;

public interface RoleRepository extends JpaRepository<TisRole, Integer> {
	
	
	public Optional<TisRole> findByName(String name);

}
