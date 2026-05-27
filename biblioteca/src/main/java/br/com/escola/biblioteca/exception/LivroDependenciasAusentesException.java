package br.com.escola.biblioteca.exception;

public class LivroDependenciasAusentesException extends RuntimeException {

    // Será substituído por um notblank no futuro, depois deleta esse cara.

    public LivroDependenciasAusentesException() {
        super("O livro precisa ter Autor, Editora e Gênero obrigatoriamente vinculados.");
    }
}