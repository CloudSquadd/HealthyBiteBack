package com.esprit.pidev.security.services;

import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.entities.UserRole.User;

public interface PasswordResetTokenService {

    PasswordResetToken createTokenForUser(User user);

    PasswordResetToken findByToken(String token);
    PasswordResetToken getPasswordResetToken(String token);

}

