package br.com.zup.MercadoLivre.exception;

public class NotTheSameOwnerException extends RuntimeException {
    private final String field;

    public NotTheSameOwnerException(String field) {
        super("Você não é o dono desse produto, por isso não pode modificá-lo");

        this.field = field;
    }

    public String getField() {
        return field;
    }
}
