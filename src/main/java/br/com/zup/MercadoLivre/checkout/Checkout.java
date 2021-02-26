package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.exception.CheckoutNotFoundException;
import br.com.zup.MercadoLivre.payment.IPayment;
import br.com.zup.MercadoLivre.payment.PagSeguro;
import br.com.zup.MercadoLivre.payment.PaymentEnum;
import br.com.zup.MercadoLivre.payment.Paypal;
import br.com.zup.MercadoLivre.product.Product;
import br.com.zup.MercadoLivre.user.User;

import javax.persistence.*;
import java.util.Optional;

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
    private PaymentEnum paymentEnum;

    @Deprecated
    public Checkout() {}

    public Checkout(
        Product product,
        Integer productQuantity,
        CheckoutStatus status,
        PaymentEnum paymentEnum
    ) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.status = status;
        this.paymentEnum = paymentEnum;

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

    public PaymentEnum getPayment() {
        return paymentEnum;
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
}
