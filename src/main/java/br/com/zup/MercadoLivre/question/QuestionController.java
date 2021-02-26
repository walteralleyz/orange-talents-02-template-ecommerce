package br.com.zup.MercadoLivre.question;

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
@RequestMapping("/api/question")
public class QuestionController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<QuestionResponseDTO> create(@RequestBody @Valid QuestionDTO dto) {
        Question question = dto.toModel(em);
        em.persist(question);

        question.sendEmailToSeller();

        return ResponseEntity.ok(question.toDTO());
    }
}
