package com.esprit.pidev.repository.UserRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.esprit.pidev.entities.UserRole.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
}
