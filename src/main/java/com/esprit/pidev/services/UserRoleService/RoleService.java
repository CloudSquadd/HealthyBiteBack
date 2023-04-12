package com.esprit.pidev.services.UserRoleService;

import com.esprit.pidev.entities.UserRole.Role;
import com.esprit.pidev.repository.UserRoleRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repo;



    public Role createNewRole(Role role) {
        return repo.save(role);
    }
}
