package br.com.escola.biblioteca.exception;

public class GeneroNaoEncontradoException extends RuntimeException {

    public GeneroNaoEncontradoException(Long id) {
        super("Gênero não encontrado com o ID: " + id);
    }
}
