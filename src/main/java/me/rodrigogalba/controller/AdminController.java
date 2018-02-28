package me.rodrigogalba.controller;

import me.rodrigogalba.controller.template.AdminPermission;
import me.rodrigogalba.model.User;
import me.rodrigogalba.repository.UserRepository;
import me.rodrigogalba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @PatchMapping(value = "/password/")
    public ResponseEntity<User> changePassword(@RequestBody User userDetails,
                                               Principal principal,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        User user = userService.updateUserPassword(userDetails.getEncryptedPassword(), userDetails.getId());
        logoutForCurrentUser(principal, request, response, user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/permission/")
    public ResponseEntity<User> changePermission(@Valid @RequestBody AdminPermission adminPermission,
                                                 Principal principal,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        User user = userService.updatePermission(adminPermission.getUserId(), adminPermission.isAdmin());

        logoutForCurrentUser(principal, request, response, user);

        return ResponseEntity.ok().build();
    }

    private void logoutForCurrentUser(Principal principal, HttpServletRequest request, HttpServletResponse response, User user) {
        if (principal.getName().equals(user.getEmail())) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }
    }
}
