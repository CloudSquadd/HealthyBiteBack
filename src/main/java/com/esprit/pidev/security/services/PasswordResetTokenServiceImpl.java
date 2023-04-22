package com.esprit.pidev.security.services;

import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.UserRoleRepository.PasswordResetTokenRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{
    private static final int EXPIRATION_IN_MINUTES = 30;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(DateUtils.addMinutes(new Date(), EXPIRATION_IN_MINUTES));
        return passwordResetTokenRepository.save(passwordResetToken);
    }
    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.getPasswordResetToken(token);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
