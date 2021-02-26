package br.com.zup.MercadoLivre.exception;

public class GenericException extends RuntimeException {
    private final String field;

    public GenericException(String field, String message) {
        super(message);

        this.field = field;
    }

    public String getField() {
        return field;
    }
}
