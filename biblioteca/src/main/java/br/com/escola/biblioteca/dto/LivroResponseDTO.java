package br.com.escola.biblioteca.dto;

public record LivroResponseDTO(
    Long id,
    String titulo,
    String isbn,
    Integer anoPublicacao,
    Long autorId,
    String autorNome,
    Long editoraId,
    String editoraNome,
    Long generoId,
    String generoNome
) {}