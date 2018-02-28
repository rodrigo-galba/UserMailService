package me.rodrigogalba.controller;

import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @PatchMapping(value = "/password/")
    public ResponseEntity<User> changePassword(@RequestBody User userDetails,
                                               Principal principal) {
        String currentUserEmail = principal.getName();
        userService.updateUserPassword(currentUserEmail, userDetails.getEncryptedPassword(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/permission/{userId}")
    public ResponseEntity<User> changePermission(@PathVariable Long userId,
                                                 @RequestBody User userDetails) {
        //TODO: To implement user permission update only by admins
        return ResponseEntity.ok().build();
    }
}
