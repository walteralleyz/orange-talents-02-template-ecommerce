package br.com.zup.MercadoLivre.rating;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<RatingResponse> create(@RequestBody @Valid RatingRequest dto) {
        Rating rating = dto.toModel(em);
        em.persist(rating);

        return ResponseEntity.ok(rating.toDTO());
    }
}
