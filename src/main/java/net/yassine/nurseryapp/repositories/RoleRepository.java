package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
