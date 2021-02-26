package br.com.zup.MercadoLivre.payment;

public class PagSeguro implements IPayment {
    @Override
    public String getLink(String id, String url) {
        return String.format("pagseguro.com?returnId=%s&redirectUrl=%s", id, url);
    }
}
