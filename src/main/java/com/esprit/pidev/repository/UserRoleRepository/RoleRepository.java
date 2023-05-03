package com.esprit.pidev.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;


import com.esprit.pidev.entities.UserRole.ERole;
import com.esprit.pidev.entities.UserRole.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
  @Query("SELECT r.name, COUNT(u) FROM User u JOIN u.roles r GROUP BY r")
  List<Object[]> countUsersByRole();



}
