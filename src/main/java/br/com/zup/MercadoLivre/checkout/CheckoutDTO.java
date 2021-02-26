package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.annotation.QuantityAvailable;
import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static br.com.zup.MercadoLivre.payment.PaymentType.verifyPayment;
import static br.com.zup.MercadoLivre.checkout.CheckoutStatus.verifyCheckout;

@QuantityAvailable
public class CheckoutDTO {
    @NotNull
    private final Integer product_id;

    @NotNull
    @Positive
    private final Integer productQuantity;

    @NotBlank
    private final String status;

    @NotBlank
    private final String payment;

    public CheckoutDTO(
        @NotNull Integer product_id,
        @NotNull @Positive Integer productQuantity,
        @NotBlank String status,
        @NotBlank String payment
    ) {
        this.product_id = product_id;
        this.productQuantity = productQuantity;
        this.status = status;
        this.payment = payment;
    }

    public Checkout toModel(EntityManager em) {
        Product product = Product.findProductById(em, product_id);
        product.setQuantity(productQuantity);

        return new Checkout(
            product,
            productQuantity,
            verifyCheckout(status),
            verifyPayment(payment)
        );
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public String getStatus() {
        return status;
    }

    public String getPayment() {
        return payment;
    }
}
