package me.rodrigogalba.controller;

import me.rodrigogalba.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/password/{userId}")
    public ResponseEntity<User> changePassword(@PathVariable Long userId,
                                               @RequestBody User userDetails) {
        //TODO: To implement user password update only by admins
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/permission/{userId}")
    public ResponseEntity<User> changePermission(@PathVariable Long userId,
                                                 @RequestBody User userDetails) {
        //TODO: To implement user permission update only by admins
        return ResponseEntity.ok().build();
    }
}
