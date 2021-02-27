package br.com.zup.MercadoLivre.security;

import br.com.zup.MercadoLivre.user.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private SecurityTokenService service;

    @PostMapping
    public ResponseEntity<SecurityTokenDTO> authentication(@RequestBody @Valid UserLoginRequest dto) {
        UsernamePasswordAuthenticationToken login = dto.converter();

        try {
            Authentication authentication = manager.authenticate(login);
            String token = service.generate(authentication);

            return ResponseEntity.ok(new SecurityTokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
