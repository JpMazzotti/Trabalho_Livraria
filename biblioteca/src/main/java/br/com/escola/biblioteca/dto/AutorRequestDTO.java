package br.com.escola.biblioteca.dto;

import jakarta.time.LocalDate;

public record AutorRequestDTO(
    String nome,
    String nacionalidade,
    LocalDate dataNascimento
) {}