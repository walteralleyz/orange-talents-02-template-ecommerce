package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.exception.GenericException;
import br.com.zup.MercadoLivre.exception.MultipleGenericException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Validator {
    private final Object domain;
    private final Map<String, Object> properties;
    private final Map<String, List<String>> state;

    public Validator(Object domain) {
        this.properties = new HashMap<>();
        this.state = new HashMap<>();
        this.domain = domain;
    }

    public Validator body(String... args) {
        try {
            for (String arg : args) {
                String field = arg.substring(0, 1).toUpperCase() + arg.substring(1);
                Method method = domain.getClass().getMethod("get" + field);
                Object value = method.invoke(domain);

                properties.putIfAbsent(arg, value);
                state.putIfAbsent(arg, new ArrayList<>());
            }

            return this;
        } catch (Exception e) {
            throw new GenericException("fields", "Campo não existe na classe.");
        }
    }

    public Validator notNull() {
        properties.forEach((key, value) -> {
            if(value == null)
                state.get(key).add("null");
        });

        return this;
    }

    public Validator notBlank() {
        properties.forEach((key, value) -> {
            if(value == null || value.toString().trim().equals(""))
                state.get(key).add("blank");
        });

        return this;
    }

    public Validator length(int min, int max) {
        properties.forEach((key, value) -> {
            if(value.toString().length() < min || value.toString().length() > max)
                state.get(key).add("length");
        });

        return this;
    }

    public Validator notNumeric() {
        properties.forEach((key, value) -> {
            if(value.toString().matches(".*\\d.*"))
                state.get(key).add("numeric");
        });

        return this;
    }

    public Validator notNegative() {
        properties.forEach((key, value) -> {
            if(value instanceof Integer && Integer.parseInt(value.toString()) < 0)
                state.get(key).add("negative");
        });

        return this;
    }

    public Validator containsEnum(Class<?> e) {
        properties.forEach((key, value) -> {
           Field[] fields = e.getDeclaredFields();

           if(!Arrays.toString(fields).contains(value.toString()))
               state.get(key).add("not in enum");
        });

        return this;
    }

    public void validate() {
        MultipleGenericException exception = new MultipleGenericException();

        state.forEach((key, value) -> {
            if(value.size() > 0) {
                exception.getFields().add(key);
                exception.getStates().add(value);
            }
        });

        properties.clear();
        state.clear();

        if(exception.getFields().size() > 0)
            throw exception;
    }
}
