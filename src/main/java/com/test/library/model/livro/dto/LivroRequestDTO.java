package com.test.library.model.livro.dto;

import com.test.library.validation.UniqueISBN;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class LivroRequestDTO {

    @NotBlank(message = "Informar o titulo do livro!")
    @Schema(description = "Nome do livro", example = "Harry Potter")
    private String titulo;

    @NotBlank(message = "Informar o isbn do livro!")
    @UniqueISBN
    @Schema(description = "Isbn do livro", example = "9780545069670")
    private String isbn;

    @Past(message = "Informar uma data no passado!")
    @Schema(description = "Data de publicação do livro", example = "2020-10-30")
    private LocalDate dataPublicacao;

    @NotNull(message = "Informar o autor!")
    private UUID autorId;
}
