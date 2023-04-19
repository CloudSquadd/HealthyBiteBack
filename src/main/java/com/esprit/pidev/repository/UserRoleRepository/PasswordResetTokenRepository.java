package com.esprit.pidev.repository.UserRoleRepository;

import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.entities.UserRole.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
    default PasswordResetToken getPasswordResetToken(String token) {
        return findByToken(token);
    }

}
