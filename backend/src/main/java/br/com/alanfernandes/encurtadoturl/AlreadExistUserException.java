package br.com.alanfernandes.encurtadoturl;

public class AlreadExistUserException extends RuntimeException {
    public AlreadExistUserException(String message) {
        super(message);
    }
}
