package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.ChildRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<ChildRegistration,Integer> {

    ChildRegistration findChildRegistrationByChildId(Integer Id);

}
