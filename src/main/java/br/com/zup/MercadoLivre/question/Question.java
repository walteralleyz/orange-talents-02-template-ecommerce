package br.com.zup.MercadoLivre.question;

import br.com.zup.MercadoLivre.product.Product;
import br.com.zup.MercadoLivre.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "questions")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private LocalDate createdAt;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;

    @ManyToOne
    private Product product;

    @Deprecated
    public Question() {}

    public Question(String title, Product product) {
        this.title = title;
        this.product = product;

        this.user = User.getActualUser();
        this.createdAt = LocalDate.now();
    }

    public QuestionResponseDTO toDTO() {
        return new QuestionResponseDTO(
            title,
            createdAt,
            user.toDTO()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void sendEmailToSeller() {
        System.out.printf("Usuário %s fez a pergunta %S. Acesse >>>/api/product/%s<<< para visualizar.%n",
            user.getUsername(), title, product.getId()
        );
    }
}
