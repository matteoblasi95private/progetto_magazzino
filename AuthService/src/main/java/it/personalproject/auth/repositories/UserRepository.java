package it.personalproject.auth.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.personalproject.auth.entities.TisUser;

@Repository
public interface UserRepository extends JpaRepository<TisUser, String> {

}
