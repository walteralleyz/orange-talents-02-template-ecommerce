package br.com.zup.MercadoLivre.rating;

import br.com.zup.MercadoLivre.product.Product;
import br.com.zup.MercadoLivre.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Deprecated
    public Rating() {}

    public Rating(Integer level, String title, String description, Product product) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.product = product;

        this.user = User.getActualUser();
    }

    public RatingResponseDTO toDTO() {
        return new RatingResponseDTO(
            level,
            title,
            description,
            user.toDTO()
        );
    }

    public Integer getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
