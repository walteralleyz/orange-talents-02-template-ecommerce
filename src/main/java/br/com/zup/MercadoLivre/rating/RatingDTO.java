package br.com.zup.MercadoLivre.rating;

import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class RatingDTO {
    @NotNull
    @Min(1) @Max(5)
    private final Integer level;

    @NotBlank
    private final String title;

    @NotBlank
    @Size(max = 500)
    private final String description;

    @NotNull
    private final Integer product_id;

    public RatingDTO(
        @NotNull @Min(1) @Max(5) Integer level,
        @NotBlank String title,
        @NotBlank @Size(max = 500) String description,
        @NotNull Integer product_id
    ) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.product_id = product_id;
    }

    public Rating toModel(EntityManager em) {
        return new Rating(
            level,
            title,
            description,
            Product.findProductById(em, product_id)
        );
    }

    public Integer getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getProduct_id() {
        return product_id;
    }
}
