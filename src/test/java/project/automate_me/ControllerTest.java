package project.automate_me;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import project.automate_me.model.*;
import project.automate_me.repository.WeatherRepository;
import project.automate_me.service.UserService;
import project.automate_me.service.WeatherService;
import project.automate_me.service.WindowService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WindowService windowService;
    private static LoginResponse JWT_TOKEN;


    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testLoginEndpoint() throws JsonProcessingException, JSONException {
        LoginRequest loginRequest = new LoginRequest("realaccount", "12345");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(jsonify(loginRequest), headers);

        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/login", HttpMethod.POST, request, String.class);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        JWT_TOKEN = dejsonify(response.getBody());
        assertThat(JWT_TOKEN.getJwtToken()).isNotNull();
    }

    @Test
    public void testGetAllUsersEndpoint() throws Exception {
        List<User> users = userService.getAllUsers();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());


        ResponseEntity<String> response =
        restTemplate.exchange(getUrl()+"/users", HttpMethod.GET,  new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(jsonify(users));
    }

    @Test
    public void testRegisterEndpoint() throws JsonProcessingException, JSONException {
        LoginRequest registerTest = new LoginRequest("testaccount", "test");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(jsonify(registerTest), headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/register", HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        User user = userService.getByUsername(registerTest.getUserName());
        assertThat(user).isNotNull();
        userService.deleteByUsername("testaccount");
    }
    @Test
    public void testChangeUserByIdEndpoint()throws JsonProcessingException, JSONException {
        User changeTest = new User("realacc","12345");
        User initialUser = userService.getUserById(2L);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(jsonify(changeTest), headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/user/2", HttpMethod.PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        User afterchangeUser =userService.getUserById(2L);
        assertThat(jsonify(initialUser)).isNotEqualTo(jsonify(afterchangeUser));
        User changeBackUser = new User ("realaccount","12345");
        userService.updateUser(changeBackUser,2L);
    }
    @Test
    public void testGetUserByIdEndpoint() throws Exception{

        User user = userService.getUserById(2L);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());

        ResponseEntity<String> response =
                restTemplate.exchange(getUrl()+"/user/2", HttpMethod.GET,  new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(jsonify(user));

    }
    @Test
    public void testGetWeatherByCityEndpoint() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());

        ResponseEntity<String> response =
                restTemplate.exchange(getUrl()+"/getWeather/Madrid", HttpMethod.GET,  new HttpEntity<>(headers), String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        Weather weather = weatherService.getWeatherByCityTest("Madrid");
        assertThat(weather).isNotNull();
        weatherService.deleteWeatherByCityTest("Madrid");

    }
    @Test
    public void testUpdateWeatherByCityEndpoint() throws Exception{

        Weather initialWeather = weatherService.getWeatherByCityTest("London");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/updateWeather/London", HttpMethod.PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        Weather afterchangeWeather =weatherService.getWeatherByCityTest("London");
        assertThat(jsonify(initialWeather)).isNotEqualTo(jsonify(afterchangeWeather));

    }
    @Test
    public void testaddWindowEndpoint() throws Exception{
        User user = new User("realaccount","12345");
        SmartWindow newWindow  = new SmartWindow("testwindow","test room","closed",0,"test_image",user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        HttpEntity<String> request = new HttpEntity<String>(jsonify(newWindow), headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/addWindow", HttpMethod.POST, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        SmartWindow window = windowService.getByNameTest("testwindow");
        assertThat(window).isNotNull();
        windowService.deleteByNameTest("testwindow");

    }


    @Test
    public void testRoomTemperatureEndpoint() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/checkRoomTemperature", HttpMethod.GET,request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);


    }
    @Test
    public void testCheckGasSmokeLevelsEndpoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/checkSmokeGasLevels", HttpMethod.GET,request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);


    }
    @Test
    public void testCheckWeatherEndpoint() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/checkWeather/Bucharest", HttpMethod.PUT, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

    }
    @Test
    public void testSetWindowPosition() throws JsonProcessingException, JSONException {
        LoginRequest loginRequest = new LoginRequest("realaccount", "12345");

        HttpHeaders headers_login = new HttpHeaders();
        headers_login.add("Content-Type","application/json");
        HttpEntity<String> request_login = new HttpEntity<String>(jsonify(loginRequest), headers_login);

        ResponseEntity<String> response_login = this.restTemplate.exchange(getUrl() + "/login", HttpMethod.POST, request_login, String.class);
        JWT_TOKEN = dejsonify(response_login.getBody());

        User user = new User("realaccount","12345");
        SmartWindow newWindow  = new SmartWindow("testwindow","test room","closed",0,"test_image",user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        HttpEntity<String> request = new HttpEntity<String>( headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/setWindowPosition/open/55", HttpMethod.GET, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        SmartWindow window = windowService.getByNameTest("testwindow");
        assertThat(window).isNotEqualTo(newWindow);
        windowService.deleteByNameTest("testwindow");
    }
    @Test
    public void testSetWindowImage(){
        User user = new User("realaccount","12345");
        SmartWindow newWindow  = new SmartWindow("testwindow","test room","closed",0,"test_image",user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        HttpEntity<String> request = new HttpEntity<String>( headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/setWindowImage/image_link", HttpMethod.GET, request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        SmartWindow window = windowService.getByNameTest("testwindow");
        assertThat(window).isNotEqualTo(newWindow);
        windowService.deleteByNameTest("testwindow");


    }
    @Test
    public void testSelfReview(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", JWT_TOKEN.getJwtToken());
        headers.add("Content-Type","application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(getUrl() + "/selfReview", HttpMethod.GET,request, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

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