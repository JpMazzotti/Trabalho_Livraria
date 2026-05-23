package br.com.escola.biblioteca.exception;

// Estende RuntimeException para não obrigar a colocar "throws" em todos os métodos
public class LivroNaoEncontradoException extends RuntimeException {
    
    public LivroNaoEncontradoException(Long id) {
        super("Livro não encontrado com o ID: " + id);
    }
}