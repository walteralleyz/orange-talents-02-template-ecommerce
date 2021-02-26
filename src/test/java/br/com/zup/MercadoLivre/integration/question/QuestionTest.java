package br.com.zup.MercadoLivre.integration.question;

import br.com.zup.MercadoLivre.integration.util.Json;
import br.com.zup.MercadoLivre.integration.util.Persistence;
import br.com.zup.MercadoLivre.question.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;

import javax.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionTest {
    private final MockMvc mvc;
    private final EntityManager em;

    private RequestBuilder requestBuilder;
    private Json json;
    private Persistence<Question> manager;

    @Autowired
    public QuestionTest(MockMvc mvc, EntityManager em) {
        this.mvc = mvc;
        this.em = em;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
        json = new Json();
        manager = new Persistence<>(Question.class, em);
    }

    @Test
    @DisplayName(value = "Cadastrar nova pergunta")
    @WithUserDetails("user@mail.com")
    public void shouldCreateNewQuestion() throws Exception {
        URI uri = new URI("/api/question");

        String content = json
            .property("title", "teste")
            .property("product_id", 1)
            .compact();

        requestBuilder.uri(uri).content(content).status(200).post();

        Question question = manager.getBy("title", "teste");
        Assertions.assertEquals("teste", question.getTitle());
        Assertions.assertEquals("pepino", question.getProduct().getName());
    }
}
