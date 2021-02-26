package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.checkout.Checkout;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Checkout checkout;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentEnum payment;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Deprecated
    public Payment() {}

    public Payment(Checkout checkout, PaymentEnum payment, String status) {
        this.checkout = checkout;
        this.payment = payment;
        this.status = status;

        this.createdAt = LocalDateTime.now();
    }

    public PaymentResponseDTO toDTO() {
        return new PaymentResponseDTO(
            checkout,
            payment,
            status,
            createdAt
        );
    }

    public Integer getId() {
        return id;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public PaymentEnum getPayment() {
        return payment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void sendEmailToClient() {
        System.out.printf("Status da Compra %d = %s.%n",
            checkout.getId(), status
        );
    }
}
