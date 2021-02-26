package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;
import br.com.zup.MercadoLivre.details.Details;
import br.com.zup.MercadoLivre.details.DetailsDTO;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRequest {
    @NotBlank
    private final String name;

    @NotNull
    @Positive
    private final BigDecimal price;

    @Min(value = 0)
    private final Integer quantity;

    @Size(min = 3)
    private final List<DetailsDTO> details;

    @NotBlank
    @Size(max = 1000)
    private final String description;

    @NotNull
    private final Integer category_id;

    public ProductRequest(
        @NotBlank String name,
        @NotNull @Positive BigDecimal price,
        @Min(value = 0) Integer quantity,
        @Size(min = 3) List<DetailsDTO> details,
        @Size(min = 1000) String description,
        @NotNull Integer category_id
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.description = description;
        this.category_id = category_id;
    }

    public Product toModel(EntityManager em) {
        return new Product(
          name,
          price,
          quantity,
          convertToProductDetails(em),
          description,
          Category.findCategoryById(em, category_id),
          null
        );
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<DetailsDTO> getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public List<Details> convertToProductDetails(EntityManager em) {
        List<Details> details = new ArrayList<>();

        this.details.forEach(dt -> {
            details.add(dt.toModel(em));
        });

        return details;
    }
}
