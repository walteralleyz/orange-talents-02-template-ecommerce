package br.com.zup.MercadoLivre.annotation;

import br.com.zup.MercadoLivre.validation.ValidationQuantity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidationQuantity.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantityAvailable {
    String message () default "Your custom message";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
