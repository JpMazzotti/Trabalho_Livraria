package br.com.escola.biblioteca.exception;

public class EditoraNaoEncontradaException extends RuntimeException {

    public EditoraNaoEncontradaException(){
        super("Editora não encontrada no sistema.");
    }
}