package com.test.library.repository;

import com.test.library.model.autor.domain.AutorDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<AutorDomain, UUID> {
}
