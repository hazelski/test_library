package com.test.library.model.autor.domain;

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
@Table(name = "autor")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AutorDomain {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", length = 128, nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 128, nullable = false)
    private String nacionalidade;

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