package br.com.escola.biblioteca.exception;


public class LivroNaoEncontradoException extends RuntimeException {
    
    public LivroNaoEncontradoException(Long id) {
        super("Livro não encontrado com o ID: " + id);
    }
}