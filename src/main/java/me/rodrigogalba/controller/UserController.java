package me.rodrigogalba.controller;

import lombok.RequiredArgsConstructor;
import me.rodrigogalba.messaging.UserMailMessage;
import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserService userService;

    @GetMapping("/")
    public List<User> index() {
        return (List<User>) repository.findAll();
    }

    @PostMapping(value = "/create")
    public User save(@Valid @RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findOne(@PathVariable Long userId) {
        User user = repository.findOne(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<User> delete(@PathVariable Long userId) {
        User user = repository.findOne(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId,
                                       @Valid @RequestBody User userDetails) {
        User user = repository.findOne(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        user.merge(userDetails);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/email")
    public ResponseEntity<User> sendEmail(@Valid @RequestBody UserMailMessage message,
                                          Principal principal) {
        userService.sendMail(principal.getName(), message);
        return ResponseEntity.ok().build();
    }
}
