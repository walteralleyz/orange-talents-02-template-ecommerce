package br.com.zup.MercadoLivre.integration.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

public class RequestBuilder {
    private final MockMvc mvc;

    private URI u;
    private String c;
    private int s;

    public RequestBuilder(MockMvc mvc) {
        this.mvc = mvc;
    }

    public RequestBuilder uri(URI uri) {
        this.u = uri;
        return this;
    }

    public RequestBuilder content(String content) {
        this.c = content;
        return this;
    }

    public RequestBuilder status(int status) {
        this.s = status;
        return this;
    }

    public String post() throws Exception {
        return expects(mvc.perform(headers(MockMvcRequestBuilders.post(u))));
    }


    public String put() throws Exception {
        return expects(mvc.perform(headers(MockMvcRequestBuilders.put(u))));
    }

    public String get() throws Exception {
        return expects(mvc.perform(headers(MockMvcRequestBuilders.get(u))));

    }

    private String expects(ResultActions actions) throws Exception {
        return actions
            .andExpect(MockMvcResultMatchers.status().is(s))
            .andReturn().getResponse().getContentAsString();
    }

    private MockHttpServletRequestBuilder headers(MockHttpServletRequestBuilder builder) {
        return builder
            .content(c)
            .header("Accept-language", "pt")
            .contentType(MediaType.APPLICATION_JSON);
    }
}
