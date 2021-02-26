package br.com.zup.MercadoLivre.validation;

public class ValidationSimpleMessage {
    private final String field;
    private final String message;

    public ValidationSimpleMessage(String f, String m) {
        field = f;
        message = m;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
