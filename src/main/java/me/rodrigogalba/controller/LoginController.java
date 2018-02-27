package me.rodrigogalba.controller;

import me.rodrigogalba.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<User> logout() {
        return ResponseEntity.ok().build();
    }
}
