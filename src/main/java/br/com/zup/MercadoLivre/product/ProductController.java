package br.com.zup.MercadoLivre.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest dto) {
        Product product = dto.toModel(em);
        em.persist(product);

        return ResponseEntity.ok(product.toDTO());
    }

    @PutMapping("/{id}/images")
    @Transactional
    public ResponseEntity<ProductImagesRequest> updateProductImages(
        @PathVariable Integer id,
        @RequestBody @Valid ProductImagesRequest dto
    ) {
        Product product = Product.findProductById(em, id);
        Product.verifySameOwner(product.getUser());

        product = dto.toModel(product, em);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id) {
        Product product = Product.findProductById(em, id);

        return ResponseEntity.ok(product.toDTO());
    }
}
