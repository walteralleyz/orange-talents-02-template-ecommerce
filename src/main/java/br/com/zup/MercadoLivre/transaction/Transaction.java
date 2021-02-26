package br.com.zup.MercadoLivre.transaction;

import br.com.zup.MercadoLivre.checkout.Checkout;
import br.com.zup.MercadoLivre.payment.PaymentType;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.zup.MercadoLivre.payment.PaymentType.verifyPayment;
import static br.com.zup.MercadoLivre.transaction.TransactionStatus.verifyTransactionStatus;

@Entity
@Table(name = "payments")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Checkout checkout;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentType payment;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Deprecated
    public Transaction() {}

    public Transaction(Checkout checkout, PaymentType payment, TransactionStatus status) {
        this.checkout = checkout;
        this.payment = payment;
        this.status = status;

        this.createdAt = LocalDateTime.now();
    }

    public TransactionResponse toDTO() {
        return new TransactionResponse(
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

    public PaymentType getPayment() {
        return payment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void sendEmailToClient() {
        System.out.printf("Status da Compra %d = %s.%n",
            checkout.getId(), status
        );
    }

    public static Transaction findPayment(String pay, Integer id, String status, EntityManager em) {
        Query query = em.createQuery("select c from Transaction c where c.payment = :pay and c.checkout.id = :id and c.status = :status");

        query.setParameter("pay", verifyPayment(pay));
        query.setParameter("id", id);
        query.setParameter("status", verifyTransactionStatus(status));

        try {
            return (Transaction) query.getSingleResult();
        } catch (NoResultException e) {
            return new Transaction();
        }
    }
}
