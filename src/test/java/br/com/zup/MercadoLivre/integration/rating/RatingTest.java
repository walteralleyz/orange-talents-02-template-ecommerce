package br.com.zup.MercadoLivre.integration.rating;

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
public class RatingTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;
    private Json json;
    private URI uri;

    @Autowired
    public RatingTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
        requestBuilder = new RequestBuilder(mvc);
        json = new Json();
        uri = new URI("/api/rating");
    }

    @Test
    @DisplayName(value = "Cadastrar opinião")
    @WithUserDetails("user@mail.com")
    public void shouldCreateRating() throws Exception {
        String content = json
            .property("level", 3)
            .property("title", "ok")
            .property("description", "not that")
            .property("product_id", 1)
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(200).post();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Cadastrar opinião com usuario diferente")
    @WithUserDetails("guest@mail.com")
    public void shouldCreateRatingWithDifferentUser() throws Exception {
        String content = json
            .property("level", 1)
            .property("title", "ok")
            .property("description", "not that")
            .property("product_id", 1)
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(200).post();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Cadastrar opinião sem login")
    public void shouldErrorTryingToCreateRatingWithoutLogin() throws Exception {
        String content = json
            .property("level", 3)
            .property("title", "ok")
            .property("description", "not that")
            .property("product_id", 1)
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(403).post();

        System.out.println(response);
    }
}
