package com.esprit.pidev.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;


import com.esprit.pidev.entities.UserRole.ERole;
import com.esprit.pidev.entities.UserRole.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  List<User> findByUsernameContainingIgnoreCase(String username);
  @Modifying
  //@Query("UPDATE User u SET u.enabled = false WHERE u.id IN (SELECT r.id FROM Role r GROUP BY r.id HAVING COUNT(r) > 3)")
  @Query("UPDATE User u SET u.enabled = false WHERE u.roles.size>=3")
  void disableUsersWithMoreThan3Roles();


  List<User> findByRolesName(String roleName);





  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  public User findByEmail(String email);
  public User findByEmailAndPassword(String email, String password);

  @Query("SELECT u FROM User u JOIN u.addresses a WHERE a.ville = :ville")
  List<User> findByVille(@Param("ville") String ville);
}
