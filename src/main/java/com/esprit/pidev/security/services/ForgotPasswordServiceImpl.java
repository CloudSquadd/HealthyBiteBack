package com.esprit.pidev.security.services;

import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.payload.response.ForgotPasswordResponse;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository repo;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public ForgotPasswordResponse sendForgotPasswordEmail(String email) {
        User user = repo.findByEmail(email);
        if (user == null) {
            return new ForgotPasswordResponse(false, "User not found");
        }

        // Generate new password and update user's password in the database
        String newPassword = generateNewPassword();
        user.setPassword(encoder.encode(newPassword));
        repo.save(user);

        // Send password reset email
        String subject = "Password Reset";
        String body = "Your new password is: " + newPassword;
        try {
            sendEmail(email, subject, body);
        } catch (MessagingException e) {
            return new ForgotPasswordResponse(false, "Failed to send email");
        }

        return new ForgotPasswordResponse(true, "Password reset email sent");
    }

    private String generateNewPassword() {

        return null;
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        mailSender.send(message);

    }
}
