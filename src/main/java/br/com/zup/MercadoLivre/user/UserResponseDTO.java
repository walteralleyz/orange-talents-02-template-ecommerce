package br.com.zup.MercadoLivre.user;

import java.time.LocalDate;

public class UserResponseDTO {
    private final String login;
    private final LocalDate createdAt;

    public UserResponseDTO(String login, LocalDate createdAt) {
        this.login = login;
        this.createdAt = createdAt;
    }

    public String getLogin() {
        return login;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
