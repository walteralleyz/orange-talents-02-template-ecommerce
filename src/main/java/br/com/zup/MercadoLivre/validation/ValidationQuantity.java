package br.com.zup.MercadoLivre.validation;

import br.com.zup.MercadoLivre.annotation.QuantityAvailable;
import br.com.zup.MercadoLivre.checkout.CheckoutRequest;
import br.com.zup.MercadoLivre.exception.ProductQuantityException;
import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidationQuantity implements ConstraintValidator<QuantityAvailable, CheckoutRequest> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(QuantityAvailable constraintAnnotation) {
    }

    @Override
    public boolean isValid(CheckoutRequest object, ConstraintValidatorContext context) {
        Product product = Product.findProductById(em, object.getProduct_id());
        boolean enough = product.getQuantity() >= object.getProductQuantity();

        if(!enough) throw new ProductQuantityException("productQuantity");

        return enough;
    }
}
