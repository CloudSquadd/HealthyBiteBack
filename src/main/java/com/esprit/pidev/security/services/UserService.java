package com.esprit.pidev.security.services;

import com.esprit.pidev.entities.UserRole.Role;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.UserRoleRepository.RoleRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService implements IUser{
    private UserRepository repo;
    @Autowired

    PasswordEncoder encoder;
    private RoleRepository rolo;
    @Autowired
    private JavaMailSender mailSender;


    public User enableUser(Long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.setEnabled(true);
        return repo.save(user);
    }

    public User disableUser(Long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        user.setEnabled(false);
        return repo.save(user);
    }
    @Transactional
    public void disableUsersWithMoreThan3Roles() {
        repo.disableUsersWithMoreThan3Roles();
    }
    @Transactional
    public void disableUsersByRoleName(String roleName) {
        List<User> users = repo.findByRolesName(roleName);
        for (User user : users) {
            user.setEnabled(false);
            repo.save(user);
        }
    }
        public User saveUser (User user){
            return repo.save(user);
        }

    public User fetchUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    @Override
    public User addUser(User user) {
        return repo.save(user);    }
    public void updatePassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        repo.save(user);
    }



    @Override
    public User retrieveUserById(Long id) {
        return repo.findById(id).orElse(null);    }

    @Override
    public List<User> retrieveAllUser() {

        return repo.findAll();    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);


    }

    @Override
    public List<User> searchUsersByUsername(String username) {
        return repo.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public User updateUser(Long id, User user, Set<String> roleNames) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update the user properties
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // ... and so on for other properties

        // Update user roles
        Set<Role> updatedRoles = new HashSet<>();
        for (Role role : existingUser.getRoles()) {
            Role existingRole = rolo.findByName(role.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            updatedRoles.add(existingRole);
        }
        existingUser.setRoles(updatedRoles);

        return repo.save(existingUser);
    }


    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);    }


}
