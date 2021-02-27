package br.com.zup.MercadoLivre.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginRequest {
    @Email
    private final String login;

    @NotBlank
    @Size(min = 6)
    private final String password;

    public UserLoginRequest(@Email String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
