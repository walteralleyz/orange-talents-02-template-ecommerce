package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.images.Images;
import br.com.zup.MercadoLivre.images.ImagesRequest;

import javax.persistence.EntityManager;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class ProductImagesRequest {
    @Size(min = 1)
    private List<ImagesRequest> images;

    @Deprecated
    public ProductImagesRequest() {}

    public ProductImagesRequest(List<ImagesRequest> images) {
        this.images = images;
    }

    public Product toModel(Product product, EntityManager em) {
        List<Images> temp = images.stream()
            .map(imagesRequest -> imagesRequest.toModel(em)).collect(Collectors.toList());

        product.setImages(temp);

        return product;
    }

    public List<ImagesRequest> getImages() {
        return images;
    }
}
