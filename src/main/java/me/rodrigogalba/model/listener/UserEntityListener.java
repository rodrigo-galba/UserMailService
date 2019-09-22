package me.rodrigogalba.model.listener;

import me.rodrigogalba.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.security.InvalidParameterException;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void encryptPassword(final User user) {
        if (user.getEncryptedPassword() == null) throw new InvalidParameterException("Senha deve ser informada.");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(user.getEncryptedPassword());
        user.setPassword(encryptedPassword);
    }
}
