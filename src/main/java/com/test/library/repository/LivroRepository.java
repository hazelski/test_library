package com.test.library.repository;

import com.test.library.model.livro.domain.LivroDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<LivroDomain, UUID> {

    long countByAutorId(UUID authorId);

    List<LivroDomain> findByAutorId(UUID autorId);

    boolean existsByIsbn(String isbn);
}
