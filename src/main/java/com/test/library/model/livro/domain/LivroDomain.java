package com.test.library.model.livro.domain;

import com.test.library.model.autor.domain.AutorDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livro")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LivroDomain {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "titulo", length = 128, nullable = false)
    private String titulo;

    @Column(name = "isbn", length = 32, nullable = false)
    private String isbn;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorDomain autor;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime  updatedAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}
