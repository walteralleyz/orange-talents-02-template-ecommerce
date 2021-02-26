package br.com.zup.MercadoLivre.rating;

import br.com.zup.MercadoLivre.user.UserResponseDTO;

public class RatingResponseDTO {
    private Integer level;
    private String title;
    private String description;
    private UserResponseDTO user;

    @Deprecated
    public RatingResponseDTO() {}

    public RatingResponseDTO(Integer level, String title, String description, UserResponseDTO user) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Integer getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserResponseDTO getUser() {
        return user;
    }
}
