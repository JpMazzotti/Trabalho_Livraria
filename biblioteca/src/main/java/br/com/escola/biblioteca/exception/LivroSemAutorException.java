package br.com.escola.biblioteca.exception;

// Estende RuntimeException para não obrigar a colocar "throws" em todos os métodos
public class LivroSemAutorException extends RuntimeException {
    
    public LivroSemAutorException() {
        super("O livro deve estar vinculado a um autor.");
    }
}