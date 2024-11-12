package com.test.library.model.autor.dto;

import com.test.library.model.autor.domain.AutorDomain;
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
public class AutorResponseDTO {

    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    public static AutorResponseDTO domainToDTO(AutorDomain domain) {
        if(domain == null)
            return null;

        return AutorResponseDTO.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .dataNascimento(domain.getDataNascimento())
                .nacionalidade(domain.getNacionalidade())
                .build();
    }
}
