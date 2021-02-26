package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.annotation.ExistsEnum;
import br.com.zup.MercadoLivre.checkout.CheckoutStatus;
import br.com.zup.MercadoLivre.exception.EnumException;
import br.com.zup.MercadoLivre.payment.PaymentEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidationEnum implements ConstraintValidator<ExistsEnum, Object> {
    String domain;

    @Override
    public void initialize(ExistsEnum params) {
        domain = params.domain();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(domain.equals("payment")) {
            try {
                PaymentEnum.valueOf(value.toString());
            } catch (IllegalArgumentException e) {
                throw new EnumException(domain);
            }
        }

        else if(domain.equals("checkout")) {
            try {
                CheckoutStatus.valueOf(value.toString());
            } catch (IllegalArgumentException e) {
                throw new EnumException(domain);
            }
        }

        return true;
    }
}
