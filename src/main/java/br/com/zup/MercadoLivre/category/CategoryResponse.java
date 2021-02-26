package br.com.zup.MercadoLivre.category;

public class CategoryResponse {
    private final String name;
    private final Category category;

    public CategoryResponse(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public CategoryResponse getCategory() {
        if(category == null) return null;
        return category.toDTO();
    }
}
