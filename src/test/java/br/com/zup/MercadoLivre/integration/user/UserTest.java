package br.com.zup.MercadoLivre.integration.user;

import br.com.zup.MercadoLivre.integration.util.Json;
import br.com.zup.MercadoLivre.integration.util.Persistence;
import br.com.zup.MercadoLivre.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;

import javax.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
    private final MockMvc mvc;
    private final EntityManager em;

    private RequestBuilder requestBuilder;
    private URI uri;
    private Json json;

    private Persistence<User> manager;

    @Autowired
    public UserTest(MockMvc mvc, EntityManager em) {
        this.mvc = mvc;
        this.em = em;
    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
        requestBuilder = new RequestBuilder(mvc);
        json = new Json();
        uri = new URI("/api/user");
        manager = new Persistence<>(User.class, em);
    }

    @Test
    @DisplayName(value = "Cadastrar usuario")
    public void deveCadastrarUmUsuario() throws Exception {
        String content = json
            .property("login", "visitor@mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(200).post();

        User user = manager.getBy("login", "visitor@mail.com");

        Assertions.assertEquals("visitor@mail.com", user.getLogin());
    }

    @Test
    @DisplayName(value = "Email repetido")
    public void deveNegarOCadastroComEmailRepetido() throws Exception {
        String content = json
            .property("login", "user@mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Email incorreto")
    public void deveRetornarErroSeEmailIncorreto() throws Exception {
        String content = json
            .property("login", "mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Senha fora do padrão")
    public void deveRetornarErroSenhaIncorreta() throws Exception {
        String content = json
            .property("login", "user@mail.com")
            .property("password", "1234")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }
}
