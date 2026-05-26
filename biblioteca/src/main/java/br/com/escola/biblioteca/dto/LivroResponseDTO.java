package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import br.com.escola.biblioteca.entity.Livro;


public record LivroResponseDTO(
    Long id,
    String titulo,
    String isbn,
    LocalDate anoPublicacao,
    Long autorId,
    String autorNome,
    Long editoraId,
    String editoraNome,
    Long generoId,
    String generoNome
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getAutor().getId(),
            livro.getAutor().getNome(),
            livro.getEditora().getId(),
            livro.getEditora().getNome(),
            livro.getGenero().getId(),
            livro.getGenero().getNome()
        );
    }
}

  

