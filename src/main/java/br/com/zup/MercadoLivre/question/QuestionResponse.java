package br.com.zup.MercadoLivre.question;

import br.com.zup.MercadoLivre.user.UserResponse;

import java.time.LocalDate;

public class QuestionResponse {
    private String title;
    private LocalDate createdAt;
    private UserResponse user;

    @Deprecated
    public QuestionResponse() {}

    public QuestionResponse(String title, LocalDate createdAt, UserResponse user) {
        this.title = title;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public UserResponse getUser() {
        return user;
    }
}
