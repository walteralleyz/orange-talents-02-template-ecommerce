package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.annotation.ExistsEnum;
import br.com.zup.MercadoLivre.annotation.QuantityAvailable;
import br.com.zup.MercadoLivre.payment.PaymentEnum;
import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@QuantityAvailable
public class CheckoutDTO {
    @NotNull
    private final Integer product_id;

    @NotNull
    @Positive
    private final Integer productQuantity;

    @NotNull
    @ExistsEnum(domain = "checkout")
    private final String status;

    @NotNull
    @ExistsEnum(domain = "payment")
    private final String payment;

    public CheckoutDTO(
        @NotNull Integer product_id,
        @NotNull @Positive Integer productQuantity,
        @NotNull String status,
        @NotNull String payment
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
            CheckoutStatus.valueOf(status),
            PaymentEnum.valueOf(payment)
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
