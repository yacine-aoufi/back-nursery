package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent,Integer> {


public Optional<Parent> findParentByEmail(String email);


}
