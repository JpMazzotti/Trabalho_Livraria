package br.com.escola.biblioteca.exception;


public class AutorInesistenteException extends RuntimeException {
    
    public AutorInesistenteException() {
        super("Autor inexistente.");
    }
}