package project.automate_me.exception;

public class UserAlreadyAddedException extends RuntimeException {
    public UserAlreadyAddedException(String username) {
        super("User: " + username + " is already registered.");
    }
}
