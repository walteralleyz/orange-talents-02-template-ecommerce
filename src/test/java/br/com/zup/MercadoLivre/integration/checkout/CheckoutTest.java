package br.com.zup.MercadoLivre.integration.checkout;

import br.com.zup.MercadoLivre.integration.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;
    private Json json;
    private URI uri;

    @Autowired
    public CheckoutTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
        requestBuilder = new RequestBuilder(mvc);
        json = new Json();
        uri = new URI("/api/checkout");
    }

    @Test
    @DisplayName(value = "Criar um checkout com quantidade alem")
    @WithUserDetails("user@mail.com")
    public void shouldNotCreateAPayment() throws Exception {
        String content = json
            .property("product_id", 1)
            .property("productQuantity", 20)
            .property("status", "INICIADA")
            .property("payment", "PAYPAL")
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(400).post();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Proibir checkout com payment incorreto")
    @WithUserDetails("user@mail.com")
    public void shouldNotCreateAPaymentWithWrongPayment() throws Exception {
        String content = json
            .property("product_id", 1)
            .property("productQuantity", 2)
            .property("status", "INICIADA")
            .property("payment", "ROBERTO")
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(400).post();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Criar um checkout")
    @WithUserDetails("user@mail.com")
    public void shouldCreateAPayment() throws Exception {
        String content = json
            .property("product_id", 1)
            .property("productQuantity", 2)
            .property("status", "INICIADA")
            .property("payment", "PAYPAL")
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(302).post();

        System.out.println(response);
    }

}
