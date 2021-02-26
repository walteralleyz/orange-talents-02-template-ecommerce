package br.com.zup.MercadoLivre.transaction;

import br.com.zup.MercadoLivre.exception.GenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static br.com.zup.MercadoLivre.transaction.Transaction.findPayment;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/{pay}/{id}/{status}")
    @Transactional
    public ResponseEntity<String> paypal(@PathVariable String pay, @PathVariable Integer id, @PathVariable String status) {
        Transaction exists = findPayment(pay, id, "SUCESSO", em);

        if(exists.getId() != null) throw new GenericException("Checkout", "A compra já foi efetuada com sucesso!");

        TransactionRequest dto = new TransactionRequest(id, pay, status);
        Transaction transaction = dto.toModel(em);
        em.persist(transaction);

        comunicarSetorNotaFiscal(transaction.getCheckout().getId(), transaction.getCheckout().getClient().getId());
        comunicarRankingVendedor(transaction.getCheckout().getId(), transaction.getCheckout().getProduct().getUser().getId());
        transaction.sendEmailToClient();

        return ResponseEntity.ok(transaction.toDTO().getStatus().toString());
    }

    public void comunicarSetorNotaFiscal(Integer idCompra, Long idUsuario) {
        System.out.printf("Compra %d realizada pelo usuário %d%n", idCompra, idUsuario);
    }

    public void comunicarRankingVendedor(Integer idCompra, Long idUsuario) {
        System.out.printf("Compra %d do vendedor %d%n", idCompra, idUsuario);
    }
}
