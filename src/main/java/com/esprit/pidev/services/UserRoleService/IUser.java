package com.esprit.pidev.services.UserRoleService;

import com.esprit.pidev.entities.UserRole.User;

import java.util.List;

public interface IUser {
    User addUser(User user);
    User updateUser(User user);
    User retrieveUserById(Long id);
    List<User> retrieveAllUser();
    void deleteUser(Long id);


}
