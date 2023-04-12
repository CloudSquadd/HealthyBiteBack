package com.esprit.pidev.repository;

import java.util.Optional;

import com.esprit.pidev.models.ERole;
import com.esprit.pidev.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
