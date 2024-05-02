package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.Child;
import net.yassine.nurseryapp.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child,Integer> {


    public Child findChildByChargilyId(String chargilyId);
    public List<Child> findByParentId(Integer Id);

}
