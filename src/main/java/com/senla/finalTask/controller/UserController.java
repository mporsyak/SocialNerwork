package com.senla.finalTask.controller;

import com.senla.finalTask.model.User;
import com.senla.finalTask.service.UserServise;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Tag(name = "")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServise userServise;

    public UserController(UserServise userServise) {
        this.userServise = userServise;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<User> userList(Model model) {
        model.addAttribute("users", userServise.findAll());
        return userServise.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        return userServise.create(user, username, form);
    }

    @PostMapping("/{id}")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        return userServise.updateProfile(user, password, email);

    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userServise.delete(user);
    }
}
