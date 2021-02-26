package br.com.zup.MercadoLivre.question;

import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionRequest {
    @NotBlank
    private final String title;

    @NotNull
    private final Integer product_id;

    public QuestionRequest(@NotBlank String title, @NotNull Integer product_id) {
        this.title = title;
        this.product_id = product_id;
    }

    public Question toModel(EntityManager em) {
        return new Question(
            title,
            Product.findProductById(em, product_id)
        );
    }

    public String getTitle() {
        return title;
    }

    public Integer getProduct_id() {
        return product_id;
    }
}
