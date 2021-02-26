package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.exception.CheckoutNotFoundException;
import br.com.zup.MercadoLivre.exception.GenericException;
import br.com.zup.MercadoLivre.payment.*;
import br.com.zup.MercadoLivre.product.Product;
import br.com.zup.MercadoLivre.transaction.Transaction;
import br.com.zup.MercadoLivre.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Checkout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Product product;

    @Column(nullable = false)
    private Integer productQuantity;

    @OneToOne
    private User client;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CheckoutStatus status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "checkout")
    private List<Transaction> transactions;

    @Deprecated
    public Checkout() {}

    public Checkout(
        Product product,
        Integer productQuantity,
        CheckoutStatus status,
        PaymentType paymentType
    ) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.status = status;
        this.paymentType = paymentType;

        this.client = User.getActualUser();
    }

    public void setStatus(CheckoutStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public User getClient() {
        return client;
    }

    public CheckoutStatus getStatus() {
        return status;
    }

    public PaymentType getPayment() {
        return paymentType;
    }

    public void sendEmailToSeller() {
        System.out.printf("Usuário %s iniciou uma compra do produto %s com status %s.%n",
            client.getUsername(), product.getName(), status
        );
    }


    public static Checkout findCheckoutById(EntityManager em, int id) {
        return Optional.ofNullable(em.find(Checkout.class, id))
            .orElseThrow(() -> new CheckoutNotFoundException("id"));
    }

    public void verifyItsAlreadySuccess() {
        List<Transaction> temp = transactions.stream().filter(payment -> payment.getStatus().equals("SUCCESS")).collect(Collectors.toList());

        if(temp.size() == 2) throw new GenericException("status", "Quantidade máxima de transações alcançada");
    }
}
