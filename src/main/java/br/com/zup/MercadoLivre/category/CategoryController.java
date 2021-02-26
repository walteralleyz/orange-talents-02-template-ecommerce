package br.com.zup.MercadoLivre.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryDTO dto) {
        Category category = dto.toModel(em);
        em.persist(category);

        return ResponseEntity.ok(category.toDTO());
    }
}
