package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.Parent;
import net.yassine.nurseryapp.entities.PreRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PreRegRepository extends JpaRepository<PreRegistration,Integer> {



}
