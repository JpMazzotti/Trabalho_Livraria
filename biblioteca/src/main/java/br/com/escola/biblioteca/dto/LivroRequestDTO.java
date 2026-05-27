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
        Integer anoPublicacao,

        @NotNull(message = "O ID do autor é obrigatório")
        Long autorId,
        
        @NotNull(message = "O ID da editora é obrigatório")
        Long editoraId,
        
        @NotNull(message = "O ID do género é obrigatório")
        Long generoId) {
}