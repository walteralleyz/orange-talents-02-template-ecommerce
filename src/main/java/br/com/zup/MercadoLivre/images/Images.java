package br.com.zup.MercadoLivre.images;

import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    private Product product;

    @Deprecated
    public Images() {}

    public Images(String link, Product product) {
        this.link = link;
        this.product = product;
    }

    public ImagesDTO toDTO() {
        return new ImagesDTO(link, product.getId());
    }

    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
