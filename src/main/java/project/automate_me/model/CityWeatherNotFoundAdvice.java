package project.automate_me.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import project.automate_me.exception.CityWeatherNotFoundException;
import project.automate_me.exception.UserNotFoundException;

@ControllerAdvice
public class CityWeatherNotFoundAdvice{
    @ResponseBody
    @ExceptionHandler(CityWeatherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CityWeatherNotFoundHandler(CityWeatherNotFoundException ex) {
        return ex.getMessage();
    }
}