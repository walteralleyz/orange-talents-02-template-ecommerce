package br.com.zup.MercadoLivre.exception;

import java.util.ArrayList;
import java.util.List;

public class MultipleGenericException extends RuntimeException {
    private final List<String> fields;
    private final List<List<String>> states;

    public MultipleGenericException() {
        super("Multiple fields got error");
        this.fields = new ArrayList<>();
        this.states = new ArrayList<>();
    }

    public List<String> getFields() {
        return fields;
    }

    public List<List<String>> getStates() {
        return states;
    }
}
