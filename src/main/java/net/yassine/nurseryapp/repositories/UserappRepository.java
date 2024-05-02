package net.yassine.nurseryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.yassine.nurseryapp.entities.UserApp;

import java.util.Optional;

public interface UserappRepository extends JpaRepository<UserApp,Integer>{

UserApp  findByEmail(String email);

 Optional<UserApp> findByUsername(String username);

}
