package project.automate_me.exception;

public class CityWeatherAlreadyAddedException extends RuntimeException {
    public CityWeatherAlreadyAddedException(String cityName) {
        super("City: " + cityName + " is already added.Please update the weather instead.");
    }
}
