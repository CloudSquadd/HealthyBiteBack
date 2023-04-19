package com.esprit.pidev.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;


import com.esprit.pidev.entities.UserRole.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  List<User> findByUsernameContainingIgnoreCase(String username);

  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  public User findByEmail(String email);
  public User findByEmailAndPassword(String email, String password);
}
