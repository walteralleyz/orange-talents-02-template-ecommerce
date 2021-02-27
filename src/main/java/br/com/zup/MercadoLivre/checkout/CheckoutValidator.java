package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.payment.PaymentType;
import br.com.zup.MercadoLivre.validation.Validator;

public class CheckoutValidator {
    private final Validator validator;

    public CheckoutValidator(Object domain) {
        this.validator = new Validator(domain);
    }

    public void execute() {
        validator.body("status", "payment").notBlank().notNumeric().validate();
        validator.body("product_id", "productQuantity").notNull().validate();
        validator.body("productQuantity").notNegative().validate();
        validator.body("status").containsEnum(CheckoutStatus.class).validate();
        validator.body("payment").containsEnum(PaymentType.class).validate();
    }
}
