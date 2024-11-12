package com.test.library.model.livro.dto;

import com.test.library.model.autor.dto.AutorResponseDTO;
import com.test.library.model.livro.domain.LivroDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO {

    private UUID id;
    private String titulo;
    private String isbn;
    private LocalDate dataPublicacao;
    private AutorResponseDTO autor;

    public static LivroResponseDTO domainToDTO(LivroDomain domain) {
        if(domain == null)
            return null;

        return LivroResponseDTO.builder()
                .id(domain.getId())
                .titulo(domain.getTitulo())
                .dataPublicacao(domain.getDataPublicacao())
                .isbn(domain.getIsbn())
                .autor(AutorResponseDTO.domainToDTO(domain.getAutor()))
                .build();
    }
}
