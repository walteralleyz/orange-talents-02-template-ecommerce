package br.com.zup.MercadoLivre.exception;

public class CategoryNotFoundException extends GenericException {
    public CategoryNotFoundException(String f) {
        super(f, "Categoria não encontrada!");
    }
}
