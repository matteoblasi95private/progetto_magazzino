package it.personalproject.auth.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import it.personalproject.auth.entities.TisUser;

public interface UserRepository extends JpaRepository<TisUser, String> {

}
