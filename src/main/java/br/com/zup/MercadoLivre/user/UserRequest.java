package br.com.zup.MercadoLivre.user;

import br.com.zup.MercadoLivre.annotation.Singular;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;

public class UserRequest {

    @NotBlank
    @Email
    @Singular(domainClass = User.class, fieldName = "login")
    private final String login;

    @NotBlank
    @Size(min = 6)
    private final String password;


    public UserRequest(
        String login,
        String password
    ) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        return new User(
            login,
            new BCryptPasswordEncoder().encode(password)
        );
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
