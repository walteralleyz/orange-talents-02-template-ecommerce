package br.com.zup.MercadoLivre.images;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.zup.MercadoLivre.product.Product.findProductById;

public class ImagesRequest {
    @NotBlank
    private String link;

    @NotNull
    private Integer product_id;

    @Deprecated
    public ImagesRequest() {}

    public ImagesRequest(@NotBlank String link, @NotNull Integer product_id) {
        ImagesValidator validator = new ImagesValidator(this);

        this.product_id = product_id;
        this.link = link;

        validator.execute();
    }

    public Images toModel(EntityManager em) {
        Images images = new Images(link, findProductById(em, product_id));
        em.persist(images);

        return images;
    }

    public String getLink() {
        return link;
    }

    public Integer getProduct_id() {
        return product_id;
    }
}
