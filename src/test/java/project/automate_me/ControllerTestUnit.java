package project.automate_me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.automate_me.model.LoginRequest;
import project.automate_me.model.LoginResponse;
import project.automate_me.model.User;
import project.automate_me.repository.UserRepository;
import project.automate_me.service.AuthenticationService;
import project.automate_me.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class ControllerTestUnit {

    @LocalServerPort
    private int port;
    private static LoginResponse JWT_TOKEN;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;
    @InjectMocks
    private AuthenticationService authenticationService;

    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testRegisterEndpoint() throws JsonProcessingException, JSONException {
        LoginRequest registerTest = new LoginRequest("testaccount", "test");
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        User savedUser = authenticationService.register(registerTest);
        assertThat(savedUser.getName()).isNotNull();
    }

    private String jsonify(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private LoginResponse dejsonify(String loginResponse) throws JsonProcessingException, JSONException {
        JSONObject obj = new JSONObject(loginResponse);
        return new LoginResponse(obj.getString("jwtToken"));

    }



}