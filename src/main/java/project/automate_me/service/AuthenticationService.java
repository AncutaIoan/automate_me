package project.automate_me.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.automate_me.config.JwtTokenGenerator;
import project.automate_me.exception.InvalidLoginDetailsException;
import project.automate_me.exception.UserAlreadyAddedException;
import project.automate_me.model.CustomUserDetails;
import project.automate_me.model.LoginRequest;
import project.automate_me.model.LoginResponse;
import project.automate_me.model.User;
import project.automate_me.repository.UserRepository;

@Service

public class AuthenticationService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    JwtTokenGenerator jwtTokenGenerator;

    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenGenerator jwtTokenGenerator) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUserName()).orElseThrow(InvalidLoginDetailsException::new);
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new InvalidLoginDetailsException();

        CustomUserDetails customUserDetails = new CustomUserDetails(user.getName());

        return new LoginResponse(jwtTokenGenerator.generateTokenWithPrefix(customUserDetails));
    }

    public User register(LoginRequest loginRequest) {
        userRepository.findByUsername(loginRequest.getUserName()).ifPresent(s -> {
            throw new UserAlreadyAddedException(loginRequest.getUserName());
        });
        User user = new User(loginRequest.getUserName(), bCryptPasswordEncoder.encode(loginRequest.getPassword()));
        return userRepository.save(user);
    }
}
