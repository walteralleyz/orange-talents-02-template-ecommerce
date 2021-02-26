package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.images.Images;
import br.com.zup.MercadoLivre.images.ImagesDTO;

import javax.persistence.EntityManager;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class ProductImagesDTO {
    @Size(min = 1)
    private List<ImagesDTO> images;

    @Deprecated
    public ProductImagesDTO() {}

    public ProductImagesDTO(List<ImagesDTO> images) {
        this.images = images;
    }

    public Product toModel(Product product, EntityManager em) {
        List<Images> temp = images.stream()
            .map(imagesDTO -> imagesDTO.toModel(em)).collect(Collectors.toList());

        product.setImages(temp);

        return product;
    }

    public List<ImagesDTO> getImages() {
        return images;
    }
}
