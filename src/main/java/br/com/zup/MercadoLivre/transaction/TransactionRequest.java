package br.com.zup.MercadoLivre.transaction;

import br.com.zup.MercadoLivre.checkout.Checkout;
import br.com.zup.MercadoLivre.checkout.CheckoutStatus;
import br.com.zup.MercadoLivre.exception.GenericException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import static br.com.zup.MercadoLivre.checkout.Checkout.findCheckoutById;
import static br.com.zup.MercadoLivre.payment.PaymentType.verifyPayment;
import static br.com.zup.MercadoLivre.transaction.TransactionStatus.verifyTransactionStatus;

public class TransactionRequest {
    @NotBlank
    private Integer checkout_id;

    @NotBlank
    private String payment;

    @NotBlank
    private String status;

    public TransactionRequest(@NotBlank Integer checkout_id, @NotBlank String payment, @NotBlank String status) {
        setPayment(payment);
        setCheckout_id(checkout_id);
        setStatus(status);
    }

    public Transaction toModel(EntityManager em) {
        Checkout checkout = findCheckoutById(em, checkout_id);
        checkout.setStatus(CheckoutStatus.CONCLUIDA);
        checkout.verifyItsAlreadySuccess();

        return new Transaction(
            checkout,
            verifyPayment(payment),
            verifyTransactionStatus(status)
        );
    }

    private void setCheckout_id(Integer id) {
        if(id == null) throw new GenericException("checkout_id", "Não pode ser nulo");
        this.checkout_id = id;
    }

    private void setStatus(String status) {
        if(status == null) throw new GenericException("status", "Não pode ser nulo");
        this.status = status;
    }

    private void setPayment(String payment) {
        if(payment == null) throw new GenericException("payment", "Campo payment deve estar presente");
        this.payment = payment;
    }


    public Integer getCheckout_id() {
        return checkout_id;
    }

    public String getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }
}
