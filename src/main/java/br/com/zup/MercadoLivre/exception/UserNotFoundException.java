package br.com.zup.MercadoLivre.exception;

public class UserNotFoundException extends GenericException {
    public UserNotFoundException(String f) {
        super(f, "User not found!");
    }
}
