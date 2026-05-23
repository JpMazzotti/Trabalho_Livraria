package br.com.escola.biblioteca.exception;


public class LivroTituloNaoNuloException extends RuntimeException {
    
    public LivroTituloNaoNuloException() {
        super("O título do livro não pode ser vazio.");
    }
}