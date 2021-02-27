package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.annotation.Singular;
import com.sun.istack.Nullable;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank
    @Singular(domainClass = Category.class, fieldName = "name")
    private final String name;

    @Nullable
    private final Integer category_id;

    public CategoryRequest(@NotBlank String name, @Nullable Integer category_id) {
        CategoryValidator validator = new CategoryValidator(this);

        this.name = name;
        this.category_id = category_id;

        validator.execute();
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
