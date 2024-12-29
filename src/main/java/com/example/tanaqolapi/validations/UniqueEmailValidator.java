package com.example.tanaqolapi.validations;

import com.example.tanaqolapi.annotations.UniqueEmail;
import com.example.tanaqolapi.repository.AppUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !appUserRepository.existsByEmail(email);
    }
}
