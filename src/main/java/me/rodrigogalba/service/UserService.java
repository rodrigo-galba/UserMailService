package me.rodrigogalba.service;

import me.rodrigogalba.messaging.UserMailMessage;
import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    UserMailService mailService;

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

    public void sendMail(String sender, UserMailMessage message) {
        List<User> admins = repository.findByIsAdmin(true);
        List<String> recipients = admins.stream()
                .map(u -> u.getEmail())
                .collect(Collectors.toList());
        message.setBccRecipients(recipients);
        message.setSender(sender);

        mailService.sendMessage(message);
    }

    public void updateUserPassword(String currentUserEmail, String newPassword, Long userId) {
        User admin = repository.findByEmail(currentUserEmail);
        if (admin == null || !admin.isAdmin()) {
            throw new RuntimeException("Permission denied for this operation.");
        }

        User user = repository.findById(userId);
        if (user == null) {
            throw new RuntimeException("Invalid user.");
        }
        //TODO: encrypt password before save in database
        String encryptedPassword = newPassword;

        if (encryptedPassword == null || encryptedPassword.trim().isEmpty()) {
            throw new RuntimeException("Invalid password.");
        }

        user.setPassword(encryptedPassword);
        repository.save(user);
    }
}
