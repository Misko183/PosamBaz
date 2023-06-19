package com.example.backend.proSecurity.emailVerification;

import com.example.backend.proSecurity.emailVerification.ConfirmationToken;
import com.example.backend.proSecurity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String > {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

    ConfirmationToken findByUser(UserEntity user);
}

