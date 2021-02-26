package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.annotation.Singular;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidationSingular implements ConstraintValidator<Singular, String> {
    String attr;
    Class<?> domainClass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(Singular params) {
        attr = params.fieldName();
        domainClass = params.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = em.createQuery(String.format(
            "SELECT c FROM %s c WHERE c.%s = :v", domainClass.getName(), attr
        ));

        query.setParameter("v", value);

        List<?> result = query.getResultList();

        return result.size() == 0;
    }
}
