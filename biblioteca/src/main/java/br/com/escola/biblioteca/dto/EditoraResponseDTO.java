package br.com.escola.biblioteca.dto;

public record EditoraResponseDTO(
        Long id,
        String nome,
        String cnpj,
        String estado
) { }