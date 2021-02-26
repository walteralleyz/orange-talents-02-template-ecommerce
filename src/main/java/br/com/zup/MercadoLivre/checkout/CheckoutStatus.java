package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.exception.EnumException;

public enum CheckoutStatus {
    INICIADA,
    CONCLUIDA;

    public static CheckoutStatus verifyCheckout(String checkout) {
        try {
            return CheckoutStatus.valueOf(checkout);
        } catch (IllegalArgumentException e) {
            throw new EnumException("checkout");
        }
    }
}
