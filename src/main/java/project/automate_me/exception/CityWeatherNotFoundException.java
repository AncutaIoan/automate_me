package project.automate_me.exception;

public class CityWeatherNotFoundException extends RuntimeException{
    public CityWeatherNotFoundException(String cityName){
        super("Could not find city: " + cityName+" .Try getting it first;");

    }
}
