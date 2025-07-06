package br.edu.ifce.meuprimeirospringboot.exceptions;

public class TurmaNaoEncontradaException extends RuntimeException {
    public TurmaNaoEncontradaException(String message) {
        super(message);
    }
}
