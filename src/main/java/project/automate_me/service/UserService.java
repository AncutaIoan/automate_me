package project.automate_me.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.automate_me.exception.UserNotFoundException;
import project.automate_me.model.User;
import project.automate_me.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public User getByUsername(String username){
        return userRepository.getByUsername(username);
    }
    public User updateUser(User newUser,Long id){
        userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setPassword( bCryptPasswordEncoder().encode(newUser.getPassword()));
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    newUser.setPassword(bCryptPasswordEncoder().encode(newUser.getPassword()));
                    return userRepository.save(newUser);
                });

    }
    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(id);
        });
        userRepository.deleteById(id);
    }
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}
