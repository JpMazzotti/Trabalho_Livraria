package br.com.escola.biblioteca.dto;

public record LivroRequestDTO(
    String titulo,
    String isbn,
    Integer anoPublicacao,
    String genero,
    Long autorId
) {}