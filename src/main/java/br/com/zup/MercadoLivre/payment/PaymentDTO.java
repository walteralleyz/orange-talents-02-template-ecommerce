package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.annotation.ExistsEnum;
import br.com.zup.MercadoLivre.checkout.Checkout;
import br.com.zup.MercadoLivre.checkout.CheckoutStatus;
import br.com.zup.MercadoLivre.exception.EnumException;
import br.com.zup.MercadoLivre.exception.GenericException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import static br.com.zup.MercadoLivre.checkout.Checkout.findCheckoutById;

public class PaymentDTO {
    @NotBlank
    private Integer checkout_id;

    @NotBlank
    @ExistsEnum(domain = "payment")
    private String payment;

    @NotBlank
    private String status;

    public PaymentDTO(@NotBlank Integer checkout_id, @NotBlank String payment, @NotBlank String status) {
        setPayment(payment);
        setCheckout_id(checkout_id);
        setStatus(status);
    }

    public Payment toModel(EntityManager em) {
        Checkout checkout = findCheckoutById(em, checkout_id);
        checkout.setStatus(CheckoutStatus.CONCLUIDA);

        return new Payment(
            checkout,
            PaymentEnum.valueOf(payment),
            status
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
        try {
            PaymentEnum.valueOf(payment);
        } catch (IllegalArgumentException e) {
            throw new EnumException("payment");
        }

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
