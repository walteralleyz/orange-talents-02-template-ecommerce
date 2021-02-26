package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.annotation.Singular;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoryDTO {

    @NotBlank
    @Singular(domainClass = Category.class, fieldName = "name")
    private final String name;

    private final Integer category_id;

    public CategoryDTO(String name, Integer category_id) {
        this.name = name;
        this.category_id = category_id;
    }

    public Category toModel(EntityManager em) {
        Category category = new Category(name);

        if(category_id != null)
            category.setCategory(Category.findCategoryById(em, category_id));

        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getCategory_id() {
        return category_id;
    }
}
