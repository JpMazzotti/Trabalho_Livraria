package br.com.escola.biblioteca.exception;

public class LivroDependenciasAusentesException extends RuntimeException {

    public LivroDependenciasAusentesException() {
        super("O livro precisa ter Autor, Editora e Gênero obrigatoriamente vinculados.");
    }
}