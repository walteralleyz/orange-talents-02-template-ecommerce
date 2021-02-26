package br.com.zup.MercadoLivre.integration.payment;

import br.com.zup.MercadoLivre.integration.util.Persistence;
import br.com.zup.MercadoLivre.integration.util.RequestBuilder;
import br.com.zup.MercadoLivre.payment.Payment;
import br.com.zup.MercadoLivre.payment.PaymentEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentTest {
    private final MockMvc mvc;
    private final EntityManager em;

    private RequestBuilder requestBuilder;
    private Persistence<Payment> manager;

    @Autowired
    public PaymentTest(MockMvc mvc, EntityManager em) {
        this.mvc = mvc;
        this.em = em;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
        manager = new Persistence<>(Payment.class, em);
    }

    @Test
    @DisplayName(value = "Criando um pagamento")
    @WithUserDetails("user@mail.com")
    public void shouldCreateAPayment() throws Exception {
        URI uri = new URI("/api/payment/PAYPAL/1/sucesso");
        requestBuilder.uri(uri).content("test").status(200).get();

        Payment payment = manager.getBy("checkout.id", 1);

        Assertions.assertEquals(PaymentEnum.PAYPAL, payment.getPayment());
    }

    @Test
    @DisplayName(value = "Enviar uma uri com recursos inválidos")
    @WithUserDetails("user@mail.com")
    public void shouldNotCreateAPaymentWithWrongResources() throws Exception {
        URI uri = new URI("/api/payment/ELO/1/sucesso");
        String response = requestBuilder.uri(uri).content("test").status(400).get();

        System.out.println(response);
    }
}
