package security.services;

import com.esprit.pidev.entities.UserRole.User;

import java.util.List;
import java.util.Set;

public interface IUser {
    User addUser(User user);
    User retrieveUserById(Long id);
    List<User> retrieveAllUser();
    void deleteUser(Long id);
    List<User> searchUsersByUsername(String username);
    User updateUser(Long id, User user, Set<String> role);

    public List<User> findByVille(String ville);
}
