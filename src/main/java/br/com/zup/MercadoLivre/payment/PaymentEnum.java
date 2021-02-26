package br.com.zup.MercadoLivre.payment;

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
}
