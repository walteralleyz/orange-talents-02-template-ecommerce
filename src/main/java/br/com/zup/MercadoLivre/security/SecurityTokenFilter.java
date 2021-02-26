package br.com.zup.MercadoLivre.security;

import br.com.zup.MercadoLivre.exception.UserNotFoundException;
import br.com.zup.MercadoLivre.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SecurityTokenFilter extends OncePerRequestFilter {
    private final SecurityTokenService tokenService;
    private final EntityManager em;

    public SecurityTokenFilter(SecurityTokenService tokenService, EntityManager em) {
        this.tokenService = tokenService;
        this.em = em;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean isValid = tokenService.validate(token);

        if(isValid)
            autenticarCliente(token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        User usuario = Optional.ofNullable(em.find(User.class, idUsuario))
            .orElseThrow(() -> new UserNotFoundException("id"));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer "))
            return null;

        return token.substring(7);
    }
}
