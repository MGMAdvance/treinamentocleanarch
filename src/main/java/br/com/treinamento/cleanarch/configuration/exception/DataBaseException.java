package br.com.treinamento.cleanarch.configuration.exception;

public class DataBaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataBaseException(String msg){
        super(msg);
    }

    public DataBaseException(String msg, Throwable cause){
        super(msg, cause);
    }
}