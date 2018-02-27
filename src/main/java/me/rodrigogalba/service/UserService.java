package me.rodrigogalba.service;

import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User authenticate(String login, String encryptedPassword) {
        User user = repository.findByLogin(login);

        if (encryptedPassword.matches(user.getEncryptedPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public Collection<? extends GrantedAuthority> getRoles(User user) {
        List<GrantedAuthority> roles = new ArrayList();
        roles.add(() -> { return Roles.USER.name(); });

        if (user.isAdmin()) {
            roles.add(() -> { return Roles.ADMIN.name(); });
        }

        return roles;
    }
}
