package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.exception.EnumException;

public enum PaymentType {
    PAYPAL(new Paypal()),
    PAGSEGURO(new PagSeguro());

    IPayment payment;

    PaymentType(IPayment payment) {
        this.payment = payment;
    }

    public IPayment getPayment() {
        return payment;
    }

    public static PaymentType verifyPayment(String payment) {
        try {
            return PaymentType.valueOf(payment);
        } catch (IllegalArgumentException e) {
            throw new EnumException("payment");
        }
    }
}
