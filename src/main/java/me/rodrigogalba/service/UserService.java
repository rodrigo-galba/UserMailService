package me.rodrigogalba.service;

import lombok.RequiredArgsConstructor;
import me.rodrigogalba.messaging.UserMailMessage;
import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.security.Roles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final UserMailService mailService;

    public User authenticate(String login, String encryptedPassword) {
        User user = repository.findByLogin(login);

        if (encryptedPassword.matches(user.getEncryptedPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> roles = new ArrayList();
        roles.add(() -> { return Roles.USER.name(); });

        if (user.isAdmin()) {
            roles.add(() -> { return Roles.ADMIN.name(); });
        }

        return roles;
    }

    public void sendMail(String sender, UserMailMessage message) {
        List<User> admins = repository.findByAdmin(true);
        List<String> recipients = admins.stream()
                .map(u -> u.getEmail())
                .collect(Collectors.toList());
        message.setBccRecipients(recipients);
        message.setSender(sender);

        mailService.sendMessage(message);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUserPassword(String newPassword, Long userId) {
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
        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public User updatePermission(Long userId, Boolean admin) {
        User user = repository.findById(userId);
        if (user == null) {
            throw new RuntimeException("Invalid user.");
        }
        user.setAdmin(admin);
        repository.save(user);
        return user;
    }
}
