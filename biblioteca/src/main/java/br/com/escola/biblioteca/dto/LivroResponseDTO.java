package br.com.escola.biblioteca.dto;

import java.time.LocalDate;


import br.com.escola.biblioteca.entity.Livro;

public record LivroResponseDTO(
    Long id,
    String titulo,
    String isbn,
    LocalDate anoPublicacao,
    Long autorId,
    String autorNome
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getAutor().getId(),
            livro.getAutor().getNome()
        );
    }
}