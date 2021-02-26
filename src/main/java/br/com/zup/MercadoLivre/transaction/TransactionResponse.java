package br.com.zup.MercadoLivre.transaction;

import br.com.zup.MercadoLivre.checkout.Checkout;
import br.com.zup.MercadoLivre.payment.PaymentType;

import java.time.LocalDateTime;

public class TransactionResponse {
    private final Checkout checkout;
    private final PaymentType payment;
    private final TransactionStatus status;
    private final LocalDateTime createdAt;

    public TransactionResponse(Checkout checkout, PaymentType payment, TransactionStatus status, LocalDateTime createdAt) {
        this.checkout = checkout;
        this.payment = payment;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
