package com.esprit.pidev.RestController.UserRoleController;

import com.esprit.pidev.entities.User;
import com.esprit.pidev.services.UserRoleService.IUser;
import com.esprit.pidev.services.UserRoleService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService service;
    private IUser iuser;


    @PostMapping("/registerUser")
    public User register(@RequestBody User user) throws Exception {
        String tempEmail=user.getEmail();
        if(tempEmail != null && !"".equals(tempEmail)){
            User userObj = service.fetchUserByEmail(tempEmail);
            if (userObj != null){
                throw new Exception("user with "+tempEmail+"does already exist");
            }
        }
        User userObj = null;
        userObj = service.saveUser(user);
        return user;
    }
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmail= user.getEmail();
        String tempPass= user.getPassword();
        User userObj = null;
        if(tempEmail != null && tempPass != null){
            userObj = service.fetchUserByEmailAndPassword(tempEmail,tempPass);
        }
        if(userObj == null){
            throw new Exception("Bad Credentials");
        }
        return userObj;
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return iuser.addUser(user);

    }
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return iuser.updateUser(user);
    }
    @GetMapping("getUserById/{id}")
    public User retrieveUserById(@PathVariable("id") Long id){
        return iuser.retrieveUserById(id);
    }

    @GetMapping("/getAllUser")
    public List<User> retrieveAllUser(){
        return iuser.retrieveAllUser();
    }
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        iuser.deleteUser(id);
    }
}
