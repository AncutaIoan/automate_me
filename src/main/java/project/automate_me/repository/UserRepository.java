package project.automate_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.automate_me.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User getByUsername(String username);
    void deleteByUsername(String username);
}