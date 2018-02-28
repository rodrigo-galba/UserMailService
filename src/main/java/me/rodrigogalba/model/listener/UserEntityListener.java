package me.rodrigogalba.model.listener;

import me.rodrigogalba.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void encryptPassword(final User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Paradoxo
        String encryptedPassword = encoder.encode(user.getEncryptedPassword());
        user.setPassword(encryptedPassword);
    }
}
