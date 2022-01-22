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
import project.automate_me.model.User;
import project.automate_me.exception.UserNotFoundException;
import project.automate_me.repository.UserRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/users")
    List<User> all() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return repository.findAll();
    }



    // Single item

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    /*
    @PutMapping("/user/{id}")\
    
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    try {
                        user.setPassword(GeneratePassword.GenerateHash(newUser.getPassword()));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    String hash= null;
                    try {
                        hash = GeneratePassword.GenerateHash(newUser.getPassword());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    newUser.setPassword(hash);
                    return repository.save(newUser);
                });
    }
    */
    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}