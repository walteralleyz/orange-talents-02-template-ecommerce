package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.validation.Validator;

public class CategoryValidator {
    private final Validator validator;

    public CategoryValidator(Object domain) {
        this.validator = new Validator(domain);
    }

    public void execute() {
        validator.body("name").notBlank().length(4, 30).validate();
    }
}
