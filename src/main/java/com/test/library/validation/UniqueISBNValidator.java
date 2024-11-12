package com.test.library.validation;

import com.test.library.repository.LivroRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueISBNValidator implements ConstraintValidator<UniqueISBN, String> {

    @Autowired
    private LivroRepository repository;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        return isbn != null && !repository.existsByIsbn(isbn);
    }
}
