package com.esprit.pidev.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;



import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



import com.esprit.pidev.entities.UserRole.ERole;

import com.esprit.pidev.entities.UserRole.Role;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.entities.UserRole.VerificationToken;
import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.payload.request.LoginRequest;
import com.esprit.pidev.payload.request.SignupRequest;
import com.esprit.pidev.payload.response.JwtResponse;
import com.esprit.pidev.payload.response.MessageResponse;
import com.esprit.pidev.repository.UserRoleRepository.RoleRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import com.esprit.pidev.security.jwt.JwtUtils;
import com.esprit.pidev.security.services.UserDetailsImpl;

import com.esprit.pidev.repository.UserRoleRepository.VerificationTokenRepository;
import com.esprit.pidev.security.services.EmailSenderService;
import com.esprit.pidev.security.services.PasswordResetTokenService;
import com.esprit.pidev.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  private EmailSenderService emailSenderService;
  @Autowired

  private VerificationTokenRepository tok;
  @Autowired
  private UserService userservice;

  @Autowired
  UserRepository userRepository;
  @Autowired
  private PasswordResetTokenService tokenService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    // Check if the user is enabled
    if (!userDetails.isEnabled()) {
      return ResponseEntity.badRequest().body("User is disabled");
    }

    String jwt = jwtUtils.generateJwtToken(authentication);

    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
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
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);

            break;
          case "restaurant":
            Role restaurantRole = roleRepository.findByName(ERole.ROLE_RESTAURANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(restaurantRole);
            break;
          case "fournisseur":
            Role FournisseurRole = roleRepository.findByName(ERole.ROLE_FOURNISSEUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(FournisseurRole);
            break;
        }
      });
    }

    user.setRoles(roles);
    user.setEnabled(true);
    userRepository.save(user);

    String token = UUID.randomUUID().toString();
    VerificationToken verificationToken = new VerificationToken(token, user);
    tok.save(verificationToken);
    String confirmationUrl = "http://localhost:8080/api/auth/confirm-account?token=" + verificationToken.getToken();; // add token to URL

    emailSenderService.sendVerificationEmail(user, verificationToken,confirmationUrl);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  @GetMapping("/user")
  public User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Optional<User> userOptional = userRepository.findByUsername(username);
    return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @GetMapping("/userObjects")
  public User getCurrentUserObjects() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Optional<User> userOptional = userRepository.findByUsername(username);
    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    //return new User(user.getUsername(), user.getPassword(), user.getEmail());
    return user;
  }

  @PostMapping("/confirm-account")
  public ResponseEntity<?> confirmAccount(@RequestParam("token") String token) {
    VerificationToken verificationToken = tok.findByToken(token);

    if (verificationToken == null) {
      return ResponseEntity.badRequest().body("Invalid token");
    }

    User user = verificationToken.getUser();
    user.setEnabled(true);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Account confirmed successfully!"));
  }
  @GetMapping("/reset-password")
  public String showResetPasswordForm(Model model, @RequestParam("token") String token) {
    // Check if the token is valid and not expired
    PasswordResetToken passwordResetToken = tokenService.getPasswordResetToken(token);
    if (passwordResetToken == null) {
      model.addAttribute("errorMessage", "Invalid or expired password reset token");
      return "error";
    }

    // Add the token to the model and display the password reset form
    model.addAttribute("token", token);
    return "reset-password";
  }
  @PostMapping("/forgot-password")
  public String processForgotPasswordForm(HttpServletRequest request, Model model, @RequestParam("email") String email) {
    // Find the user by email
    User user = userservice.findByEmail(email);
    if (user == null) {
      model.addAttribute("errorMessage", "No user found with email: " + email);
      return "forgot-password";
    }

    PasswordResetToken passwordResetToken = tokenService.createTokenForUser(user);

    // Create password reset URL with token
    String resetUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + "/reset-password?token=" + passwordResetToken.getToken();

    // Send an email to the user with the password reset link
    emailSenderService.sendResetPasswordEmail(user, passwordResetToken,resetUrl);

    // Display a success message and redirect to the login page
    model.addAttribute("successMessage", "An email with instructions to reset your password has been sent to " + email);
    return "redirect:/login";
  }
}

