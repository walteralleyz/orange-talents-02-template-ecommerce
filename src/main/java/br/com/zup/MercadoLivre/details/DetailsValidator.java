package br.com.zup.MercadoLivre.details;

import br.com.zup.MercadoLivre.validation.Validator;

public class DetailsValidator {
    private final Validator validator;

    public DetailsValidator(Object domain) {
        this.validator = new Validator(domain);
    }

    public void execute() {
        validator.body("title", "text").notBlank().validate();
    }
}
