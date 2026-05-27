package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record LivroRequestDTO(
        @NotBlank(message = "O título não pode estar em branco")
        String titulo,

        @NotBlank(message = "O código ISBN não pode estar em branco")
        String isbn,

        @Past(message = "A data de publicação precisa estar no passado")
        @NotNull(message = "O ano de publicação deve existir")
        LocalDate anoPublicacao,

        Long autorId,
        Long editoraId,
        Long generoId) {
}