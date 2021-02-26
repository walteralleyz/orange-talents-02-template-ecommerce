package br.com.zup.MercadoLivre.integration.util;

public class Token {
    public static String extractToken(String token) {
        String splatted = token.split(",")[0];

        return splatted.split(":")[1].replaceAll("\"", "");
    }
}
