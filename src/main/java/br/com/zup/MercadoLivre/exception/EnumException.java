package br.com.zup.MercadoLivre.exception;

public class EnumException extends GenericException {
    public EnumException(String field) {
        super(field, "Campo inválido. Favor informar um valor existente");
    }
}
