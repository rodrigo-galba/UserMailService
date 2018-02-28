package me.rodrigogalba.controller;

import me.rodrigogalba.controller.template.AdminPermission;
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
    public ResponseEntity<User> changePassword(@RequestBody User userDetails) {
        userService.updateUserPassword(userDetails.getEncryptedPassword(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/permission/")
    public ResponseEntity<User> changePermission(@Valid @RequestBody AdminPermission adminPermission) {
        userService.updatePermission(adminPermission.getUserId(), adminPermission.isAdmin());
        return ResponseEntity.ok().build();
    }
}
