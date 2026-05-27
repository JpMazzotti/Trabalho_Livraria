package br.com.escola.biblioteca.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record LivroRequestDTO(
        @NotBlank(message = "O título não pode estar em branco")
        String titulo,

        @NotBlank(message = "O código ISBN não pode estar em branco")
        String isbn,

        
        @NotNull(message = "O ano de publicação deve existir")
        Integer anoPublicacao,

        @NotNull(message = "O ID do autor é obrigatório")
        Long autorId,
        
        @NotNull(message = "O ID da editora é obrigatório")
        Long editoraId,
        
        @NotNull(message = "O ID do género é obrigatório")
        Long generoId) {
}