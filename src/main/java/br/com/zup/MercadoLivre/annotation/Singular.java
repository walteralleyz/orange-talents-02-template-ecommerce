package br.com.zup.MercadoLivre.annotation;

import br.com.zup.MercadoLivre.validation.ValidationSingular;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidationSingular.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Singular {
    String message() default "{br.com.zup.MercadoLivre.Singular}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> domainClass();
    String fieldName();
}
