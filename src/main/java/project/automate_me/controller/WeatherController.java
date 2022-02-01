package project.automate_me.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.automate_me.model.*;
import project.automate_me.service.WeatherService;

import java.io.IOException;

@RestController
public class WeatherController {
    private WeatherService weatherService;
    public WeatherController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping("/getWeather/{cityName}")
    public Weather getWeatherByCity(@PathVariable String cityName) throws IOException, JSONException {
        return weatherService.getWeatherByCity(cityName);
    }
    @PutMapping("/updateWeather/{cityName}")
    public Weather updateWeatherByCity(@PathVariable String cityName) throws IOException, JSONException {
        return weatherService.updateWeatherByCity(cityName);
    }
    @PutMapping("/checkWeather/{cityName}")
    public SmartWindow checkWeather(@PathVariable String cityName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
        return weatherService.checkWeather(cityName,username);
    }
}
