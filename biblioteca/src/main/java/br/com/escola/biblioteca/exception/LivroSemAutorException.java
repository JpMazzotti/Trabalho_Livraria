package br.com.escola.biblioteca.exception;


public class LivroSemAutorException extends RuntimeException {
    
    public LivroSemAutorException() {
        super("O livro deve estar vinculado a um autor.");
    }
}