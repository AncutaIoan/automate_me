package project.automate_me.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import project.automate_me.exception.CityWeatherAlreadyAddedException;
import project.automate_me.exception.UserAlreadyAddedException;

@ControllerAdvice
public class CityWeatherAlreadyAddedAdvice {
    @ResponseBody
    @ExceptionHandler(CityWeatherAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CityWeatherAlreadyAddedHandler(CityWeatherAlreadyAddedException ex) {
        return ex.getMessage();
    }
}
