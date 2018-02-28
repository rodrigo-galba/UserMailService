package me.rodrigogalba.security;

import me.rodrigogalba.model.User;
import me.rodrigogalba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String encryptedPassword = authentication.getCredentials().toString();


        User user = service.authenticate(login, encryptedPassword);
        if (user != null) {
            Collection<? extends GrantedAuthority> authorities = service.getAuthorities(user);
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getEncryptedPassword(), authorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
