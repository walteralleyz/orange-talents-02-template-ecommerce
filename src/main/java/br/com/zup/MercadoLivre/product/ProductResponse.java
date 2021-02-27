package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;
import br.com.zup.MercadoLivre.details.DetailsRequest;
import br.com.zup.MercadoLivre.images.Images;
import br.com.zup.MercadoLivre.question.Question;
import br.com.zup.MercadoLivre.rating.Rating;
import br.com.zup.MercadoLivre.rating.RatingResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;
    private final List<DetailsRequest> details;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Images> images;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Question> questions;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Rating> ratings;

    private final String description;
    private final Category category;
    private final LocalDate createdAt;

    public ProductResponse(
        String name,
        BigDecimal price,
        Integer quantity,
        List<DetailsRequest> details,
        List<Images> images,
        List<Question> questions,
        List<Rating> ratings,
        String description,
        Category category,
        LocalDate createdAt
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.images = images;
        this.questions = questions;
        this.ratings = ratings;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<DetailsRequest> getDetails() {
        return details;
    }

    public List<?> getImages() {
        if(images == null) return images;
        return images.stream().map(Images::toDTO).collect(Collectors.toList());
    }

    public List<?> getQuestions() {
        if(questions == null) return questions;
        return questions.stream().map(Question::toDTO).collect(Collectors.toList());
    }

    public List<?> getRatings() {
        if(ratings == null) return ratings;
        return ratings.stream().map(Rating::toDTO).collect(Collectors.toList());
    }

    public Double getRatingsAvarage() {
        if(ratings == null || ratings.isEmpty()) return 0.00;

        List<RatingResponse> temp = (List<RatingResponse>) getRatings();
        return temp.stream().mapToDouble(RatingResponse::getLevel).sum() / ratings.size();
    }

    public Integer getTotalRatings() {
        if(ratings == null) return 0;

        return ratings.size();
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
