package br.com.zup.MercadoLivre.rating;

import br.com.zup.MercadoLivre.user.UserResponse;

public class RatingResponse {
    private Integer level;
    private String title;
    private String description;
    private UserResponse user;

    @Deprecated
    public RatingResponse() {}

    public RatingResponse(Integer level, String title, String description, UserResponse user) {
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

    public UserResponse getUser() {
        return user;
    }
}
