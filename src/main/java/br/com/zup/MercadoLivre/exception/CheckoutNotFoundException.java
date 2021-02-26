package br.com.zup.MercadoLivre.exception;

public class CheckoutNotFoundException extends GenericException {
    public CheckoutNotFoundException(String field) {
        super(field, "Compra não encontrada");
    }
}
