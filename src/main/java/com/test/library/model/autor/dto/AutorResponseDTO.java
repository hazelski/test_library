package com.test.library.model.autor.dto;

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
}
