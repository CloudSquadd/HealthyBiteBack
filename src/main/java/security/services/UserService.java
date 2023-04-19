package security.services;

import com.esprit.pidev.entities.UserRole.ERole;
import com.esprit.pidev.entities.UserRole.Role;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.UserRoleRepository.RoleRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public User saveUser(User user) {
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
    public User updateUser(Long id, User user, Set<String> roles) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        if (!StringUtils.isEmpty(user.getPassword())) {
            existingUser.setPassword(encoder.encode(user.getPassword()));
        }

        Set<Role> userRoles = new HashSet<>();
        if (roles != null) {
            roles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = rolo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userRoles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = rolo.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userRoles.add(modRole);
                        break;
                    default:
                        Role userRole = rolo.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userRoles.add(userRole);
                        break;
                }
            });
        } else {
            Role userRole = rolo.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            userRoles.add(userRole);
        }
        existingUser.setRoles(userRoles);

        User updatedUser = repo.save(existingUser);
        return updatedUser;
    }





}
