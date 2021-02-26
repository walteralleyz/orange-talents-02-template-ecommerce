package br.com.zup.MercadoLivre.question;

import br.com.zup.MercadoLivre.user.UserResponseDTO;

import java.time.LocalDate;

public class QuestionResponseDTO {
    private String title;
    private LocalDate createdAt;
    private UserResponseDTO user;

    @Deprecated
    public QuestionResponseDTO() {}

    public QuestionResponseDTO(String title, LocalDate createdAt, UserResponseDTO user) {
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

    public UserResponseDTO getUser() {
        return user;
    }
}
