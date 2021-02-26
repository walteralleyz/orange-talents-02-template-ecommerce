package br.com.zup.MercadoLivre.integration.category;

import br.com.zup.MercadoLivre.category.Category;
import br.com.zup.MercadoLivre.integration.util.Json;
import br.com.zup.MercadoLivre.integration.util.Persistence;
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
public class CategoryTest {
    private final MockMvc mvc;
    private final EntityManager em;

    private RequestBuilder requestBuilder;
    private Json json;
    private Persistence<Category> manager;

    @Autowired
    public CategoryTest(MockMvc mvc, EntityManager em) {
        this.mvc = mvc;
        this.em = em;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
        json = new Json();
        manager = new Persistence<>(Category.class, em);
    }

    @Test
    @DisplayName(value = "Cadastrar categorias com mãe")
    @WithUserDetails("user@mail.com")
    public void deveriaCadastrarCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String content = json
            .property("name", "molho")
            .property("category_id", 1)
            .compact();

        requestBuilder.uri(uri).content(content).status(200).post();

        Category category = manager.getBy("name", "molho");
        Assertions.assertEquals("molho", category.getName());
        Assertions.assertEquals("comida", category.getCategory().getName());
    }
}
