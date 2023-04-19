package com.esprit.pidev.controllers;


import com.esprit.pidev.entities.UserRole.ERole;
import com.esprit.pidev.entities.UserRole.Role;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.payload.request.SignupRequest;
import com.esprit.pidev.payload.response.MessageResponse;
import com.esprit.pidev.repository.UserRoleRepository.RoleRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import com.esprit.pidev.security.services.IUser;
import com.esprit.pidev.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;


  @Autowired

  private UserService service;
  @Autowired
  private IUser iuser;
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @PostMapping("/addUser")
  public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signUpRequest){
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "restaurant":
            Role restaurantRole = roleRepository.findByName(ERole.ROLE_RESTAURANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(restaurantRole);

            break;
          case "fournisseur":
            Role fournisseurRole = roleRepository.findByName(ERole.ROLE_FOURNISSEUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(fournisseurRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User added successfully!"));
  }


  //@PutMapping("/updateUser")
  //public User updateUser(@RequestBody User user){
  //return iuser.updateUser(user);
  //}
  @GetMapping("getUserById/{id}")
  public User retrieveUserById(@PathVariable("id") Long id){
    return iuser.retrieveUserById(id);
  }

  @GetMapping("/getAllUser")
  public List<User> retrieveAllUser(){
    return iuser.retrieveAllUser();
  }
  @DeleteMapping("deleteUser/{id}")
  public void deleteUser(@PathVariable("id") Long id){
    iuser.deleteUser(id);
  }
  @GetMapping("/users/search")
  public List<User> searchUsersByUsername(@RequestParam("username") String username) {
    return iuser.searchUsersByUsername(username);
  }
  @PutMapping("/update/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User updatedUser, @RequestParam(required = false) Set<String> roles) {
    return service.updateUser(id, updatedUser, roles);
  }

  @GetMapping("/users/{ville}")
  public List<User> getUsersByVille(@PathVariable("ville") String ville) {
    return service.findByVille(ville);
  }


}
