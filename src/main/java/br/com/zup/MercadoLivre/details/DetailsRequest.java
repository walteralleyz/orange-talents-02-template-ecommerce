package br.com.zup.MercadoLivre.details;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class DetailsRequest {
    @NotBlank
    private final String title;

    @NotBlank
    private final String text;

    public DetailsRequest(@NotBlank String title, @NotBlank String text) {
        DetailsValidator validator = new DetailsValidator(this);

        this.title = title;
        this.text = text;

        validator.execute();
    }

    public Details toModel(EntityManager em) {
        Details details = new Details(title, text);
        em.persist(details);

        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
