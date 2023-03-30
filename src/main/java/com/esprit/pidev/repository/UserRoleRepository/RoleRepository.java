package com.esprit.pidev.repository.UserRoleRepository;


import com.esprit.pidev.entities.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
