package br.com.zup.MercadoLivre.security;

public class SecurityTokenDTO {
    private final String token;
    private final String type;

    public SecurityTokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
