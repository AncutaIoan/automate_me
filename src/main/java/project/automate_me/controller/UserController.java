package project.automate_me.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.automate_me.exception.UserAlreadyAddedException;
import project.automate_me.model.CustomUserDetails;
import project.automate_me.model.LoginRequest;
import project.automate_me.model.User;
import project.automate_me.exception.UserNotFoundException;
import project.automate_me.repository.UserRepository;
import project.automate_me.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserService userService;

    public UserController(UserRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    public List<User> all() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getAllUsers();
    }


    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.updateUser(newUser,id);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}