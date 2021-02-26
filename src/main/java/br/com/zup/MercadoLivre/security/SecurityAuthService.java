package br.com.zup.MercadoLivre.security;

import br.com.zup.MercadoLivre.exception.UserNotFoundException;
import br.com.zup.MercadoLivre.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SecurityAuthService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UserNotFoundException {
        return Optional.ofNullable(em.createQuery("from User where login = :value", User.class)
            .setParameter("value", login)
            .getSingleResult()).orElseThrow(() -> new UserNotFoundException("login"));
    }
}
