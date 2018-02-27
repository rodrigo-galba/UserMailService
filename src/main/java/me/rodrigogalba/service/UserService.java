package me.rodrigogalba.service;

import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean authenticate(String login, String encryptedPassword) {
        User user = repository.findByLogin(login);

        return (encryptedPassword.matches(user.getEncryptedPassword()));
    }
}
