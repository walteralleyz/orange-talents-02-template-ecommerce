package br.com.zup.MercadoLivre.security;

import br.com.zup.MercadoLivre.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityTokenService {

    @Value("${ml.jwt.expiration}")
    private String expiration;

    @Value("${ml.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expires = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
            .setIssuer("Mercado Livre")
            .setSubject(logged.getId().toString())
            .setIssuedAt(today)
            .setExpiration(expires)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
