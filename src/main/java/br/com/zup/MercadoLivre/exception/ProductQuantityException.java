package br.com.zup.MercadoLivre.exception;

public class ProductQuantityException extends GenericException {
    public ProductQuantityException(String field) {
        super(field, "Quantidade fora de estoque");
    }
}
