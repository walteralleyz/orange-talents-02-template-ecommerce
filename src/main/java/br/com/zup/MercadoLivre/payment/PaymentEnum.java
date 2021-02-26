package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.exception.EnumException;

public enum PaymentEnum {
    PAYPAL(new Paypal()),
    PAGSEGURO(new PagSeguro());

    IPayment payment;

    PaymentEnum(IPayment payment) {
        this.payment = payment;
    }

    public IPayment getPayment() {
        return payment;
    }

    public static PaymentEnum verifyPayment(String payment) {
        try {
            return PaymentEnum.valueOf(payment);
        } catch (IllegalArgumentException e) {
            throw new EnumException("payment");
        }
    }
}
