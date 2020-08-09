package com.diplomski.repository;

import com.diplomski.model.User;
import com.diplomski.registration.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByConfirmationToken(String confirmationToken);

    VerificationToken findByUser(User user);
}