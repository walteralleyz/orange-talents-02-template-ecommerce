package br.com.zup.MercadoLivre.details;

import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Details {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    private Product product;

    @Deprecated
    public Details() {}

    public Details(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public DetailsDTO toDTO() {
        return new DetailsDTO(title, text);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Product getProduct() {
        return product;
    }

    public String getText() {
        return text;
    }
}
