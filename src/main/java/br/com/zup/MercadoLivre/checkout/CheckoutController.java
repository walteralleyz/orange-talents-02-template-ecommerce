package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.payment.IPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid CheckoutDTO dto, UriComponentsBuilder builder) throws URISyntaxException {
        Checkout checkout = dto.toModel(em);
        checkout.sendEmailToSeller();
        em.persist(checkout);
        IPayment payment = checkout.getPayment().getPayment();

        String paymentUri = String.format("/payment/%s/{id}", checkout.getPayment().toString());

        URI uri = new URI(payment.getLink(
            checkout.getId().toString(),
            builder.path(paymentUri).buildAndExpand(checkout.getId()).toString()
        ));

        return ResponseEntity.status(302).location(uri).build();
    }
}
