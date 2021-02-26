package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.checkout.Checkout;

import java.time.LocalDateTime;

public class PaymentResponseDTO {
    private final Checkout checkout;
    private final PaymentEnum payment;
    private final String status;
    private final LocalDateTime createdAt;

    public PaymentResponseDTO(Checkout checkout, PaymentEnum payment, String status, LocalDateTime createdAt) {
        this.checkout = checkout;
        this.payment = payment;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public PaymentEnum getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
