package com.esprit.pidev.repository.UserRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esprit.pidev.entities.UserRole.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);


}
