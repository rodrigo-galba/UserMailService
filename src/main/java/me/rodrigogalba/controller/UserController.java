package me.rodrigogalba.controller;

import me.rodrigogalba.messaging.UserMailMessage;
import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.service.UserMailService;
import me.rodrigogalba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> index() {
        return (List<User>) repository.findAll();
    }

    @PostMapping(value = "/")
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
