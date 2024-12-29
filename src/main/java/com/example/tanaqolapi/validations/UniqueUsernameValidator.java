package com.example.tanaqolapi.validations;

import com.example.tanaqolapi.annotations.UniqueUsername;
import com.example.tanaqolapi.repository.AppUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername , String> {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !appUserRepository.existsByUsername(username);
    }
}
