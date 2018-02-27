package me.rodrigogalba.security;

import me.rodrigogalba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String encryptedPassword = authentication.getCredentials().toString();


        if (service.authenticate(login, encryptedPassword)) {
            Collection<? extends GrantedAuthority> authorities = new ArrayList();
            return new UsernamePasswordAuthenticationToken(
                    login, encryptedPassword, authorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
