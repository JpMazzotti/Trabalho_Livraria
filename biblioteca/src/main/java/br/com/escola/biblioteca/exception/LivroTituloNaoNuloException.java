package br.com.escola.biblioteca.exception;

// Estende RuntimeException para não obrigar a colocar "throws" em todos os métodos
public class LivroTituloNaoNuloException extends RuntimeException {
    
    public LivroTituloNaoNuloException() {
        super("O título do livro não pode ser nulo.");
    }
}