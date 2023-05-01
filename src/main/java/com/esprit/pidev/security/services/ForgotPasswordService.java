package com.esprit.pidev.security.services;

import com.esprit.pidev.entities.UserRole.PasswordResetToken;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.payload.response.ForgotPasswordResponse;

public interface ForgotPasswordService {


    ForgotPasswordResponse sendForgotPasswordEmail(String email);

}
