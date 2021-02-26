package br.com.zup.MercadoLivre.category;

public class CategoryResponseDTO {
    private final String name;
    private final Category category;

    public CategoryResponseDTO(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public CategoryResponseDTO getCategory() {
        if(category == null) return null;
        return category.toDTO();
    }
}
