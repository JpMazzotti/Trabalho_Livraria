package br.com.escola.biblioteca.exception;

// Estende RuntimeException para não obrigar a colocar "throws" em todos os métodos
public class AutorInesistenteException extends RuntimeException {
    
    public AutorInesistenteException() {
        super("Autor inexistente.");
    }
}