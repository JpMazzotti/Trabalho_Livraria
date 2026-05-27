package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDTO(
    @NotBlank(message = "O título do livro é obrigatório.")
    String titulo,

    @NotBlank(message = "O ISBN do livro é obrigatório.")
    String isbn,

    @NotNull(message = "O ano de publicação do livro é obrigatório.")
    Integer anoPublicacao,

    @NotNull(message = "O ID do autor é obrigatório.")
    Long autorId,

    @NotNull(message = "O ID da editora é obrigatório.")
    Long editoraId,

    @NotNull(message = "O ID do gênero é obrigatório.")
    Long generoId
) {}