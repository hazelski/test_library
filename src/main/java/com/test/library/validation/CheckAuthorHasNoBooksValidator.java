package com.test.library.validation;

import com.test.library.repository.LivroRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CheckAuthorHasNoBooksValidator implements ConstraintValidator<CheckAuthorHasNoBooks, UUID> {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public boolean isValid(UUID authorId, ConstraintValidatorContext context) {
        return livroRepository.countByAutorId(authorId) == 0;
    }
}
