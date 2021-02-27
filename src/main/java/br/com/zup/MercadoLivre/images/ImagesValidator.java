package br.com.zup.MercadoLivre.images;

import br.com.zup.MercadoLivre.validation.Validator;

public class ImagesValidator {
    private final Validator validator;

    public ImagesValidator(Object domain) {
        this.validator = new Validator(domain);
    }

    public void execute() {
        validator.body("product_id").notNull().validate();
        validator.body("link").notBlank().validate();
    }
}
