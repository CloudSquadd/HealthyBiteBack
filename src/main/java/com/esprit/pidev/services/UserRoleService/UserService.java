package com.esprit.pidev.services.UserRoleService;

import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUser {

    private UserRepository repo;



    public User saveUser(User user) {
        return repo.save(user);
    }

    public User fetchUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repo.save(user);
    }

    @Override
    public User retrieveUserById(Long id) {
        return null;
    }


    @Override
    public List<User> retrieveAllUser() {
        return repo.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }



}


