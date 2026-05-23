package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

public record LivroRequestDTO(
    String titulo,
    String isbn,
    LocalDate anoPublicacao,
    String genero,
    Long autorId
) {}