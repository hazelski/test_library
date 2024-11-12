package com.test.library.model.autor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutorRequestDTO {

    @NotBlank(message = "Informar o nome do autor!")
    @Schema(description = "Nome do autor", example = "J.K. Rowling")
    private String nome;
    @Past(message = "Informar uma data no passado!")
    @Schema(description = "Data de nascimento do autor", example = "1965-07-31")
    private LocalDate dataNascimento;
    @Schema(description = "Nacionalidade do autor", example = "Britânica")
    private String nacionalidade;
}
