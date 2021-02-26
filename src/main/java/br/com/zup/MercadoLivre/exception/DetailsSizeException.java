package br.com.zup.MercadoLivre.exception;

public class DetailsSizeException extends GenericException {
    public DetailsSizeException(String field) {
        super(field, "Details size have to be 2 (key, value) and title/text key have to be present");
    }
}
