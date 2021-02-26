package br.com.zup.MercadoLivre.exception;

public class ProductNotFoundException extends GenericException {
    public ProductNotFoundException(String field) {
        super(field, "Produto não encontrado");
    }
}
