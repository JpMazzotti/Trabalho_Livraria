package br.com.escola.biblioteca.exception;

public class ExclusaoNaoPermitidaException extends RuntimeException {
    
    public ExclusaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}