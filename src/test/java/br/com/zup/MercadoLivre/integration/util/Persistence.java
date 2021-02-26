package br.com.zup.MercadoLivre.integration.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Persistence<T> {
    private final EntityManager em;
    private final Class<?> domain;

    public Persistence(Class<?> domain, EntityManager em) {
        this.domain = domain;
        this.em = em;
    }

    public T getBy(String field, String value) {
        Query query = em.createQuery(String.format(
            "select c from %s c where c.%s = :value", domain.getName(), field
        ));

        query.setParameter("value", value);

        return (T) query.getSingleResult();
    }

    public T getBy(String field, Integer value) {
        Query query = em.createQuery(String.format(
            "select c from %s c where c.%s = :value", domain.getName(), field
        ));

        query.setParameter("value", value);

        return (T) query.getSingleResult();
    }

    public T getBy(String field, Enum value) {
        Query query = em.createQuery(String.format(
            "select c from %s c where c.%s = :value", domain.getName(), field
        ));

        query.setParameter("value", value);

        return (T) query.getSingleResult();
    }
}
