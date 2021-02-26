package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.annotation.Exists;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidationExists implements ConstraintValidator<Exists, Integer> {
    String attr;
    Class<?> domain;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(Exists params) {
        attr = params.fieldName();
        domain = params.domainClass();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        Query query = em.createQuery(String.format(
            "SELECT c FROM %s c WHERE c.%s = :v", domain.getName(), attr
        ));

        query.setParameter("v", value);

        try {
            Object result = query.getSingleResult();

            return result != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
