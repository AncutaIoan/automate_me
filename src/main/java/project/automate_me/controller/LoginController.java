package project.automate_me.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.automate_me.exception.UserAlreadyAddedException;
import project.automate_me.model.LoginRequest;
import project.automate_me.model.LoginResponse;
import project.automate_me.model.User;
import project.automate_me.service.AuthenticationService;


import java.security.NoSuchAlgorithmException;

@RestController
public class LoginController {
    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse requestLogin(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/register")
    User newUser(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        return authenticationService.register(loginRequest);
    }
}

