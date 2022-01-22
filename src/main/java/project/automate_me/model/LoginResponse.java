package project.automate_me.model;

import lombok.Data;

@Data
public class LoginResponse {
    String jwtToken;

    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
