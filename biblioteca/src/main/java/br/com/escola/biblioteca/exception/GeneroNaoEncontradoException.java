package br.com.escola.biblioteca.exception;

public class GeneroNaoEncontradoException extends RuntimeException {

    public GeneroNaoEncontradoException() {
        super("Gênero de livro não encontrado");
    }
}
