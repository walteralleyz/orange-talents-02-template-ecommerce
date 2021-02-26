package br.com.zup.MercadoLivre.annotation;

import br.com.zup.MercadoLivre.validation.ValidationEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidationEnum.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExistsEnum {
    String message() default "{br.com.zup.MercadoLivre.ExistsEnum}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String domain();
}
