package project.automate_me.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.automate_me.exception.CityWeatherAlreadyAddedException;
import project.automate_me.exception.CityWeatherNotFoundException;
import project.automate_me.exception.UserAlreadyAddedException;

import project.automate_me.exception.UserNotFoundException;
import project.automate_me.model.LoginRequest;
import project.automate_me.model.SmartWindow;
import project.automate_me.model.User;
import project.automate_me.model.Weather;
import project.automate_me.repository.UserRepository;
import project.automate_me.repository.WeatherRepository;
import project.automate_me.repository.WindowRepository;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.Scanner;

@Service
@Transactional
public class WeatherService {
    WeatherRepository weatherRepository;
    WindowRepository windowRepository;
    UserRepository userRepository;
    public WeatherService(WeatherRepository weatherRepository,WindowRepository windowRepository,UserRepository userRepository){
        this.weatherRepository=weatherRepository;
        this.windowRepository=windowRepository;
        this.userRepository=userRepository;
    }
    public Weather getWeatherByCityTest(String cityName){
        return weatherRepository.getByCity(cityName);
    }
    public void deleteWeatherByCityTest(String cityName){
         weatherRepository.deleteByCity(cityName);
    }
    public Weather getWeatherByCity(String cityName) throws IOException, JSONException {
        //System.out.println(cityName);
        weatherRepository.findByCity(cityName).ifPresent(s -> {
            throw new CityWeatherAlreadyAddedException(cityName);
        });
        String s = " https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=9f84de1c539b550aa161c8bde690f343";
        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext()) {
            
            str += scan.nextLine();
        }
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        // get the first result
        JSONObject res = obj.getJSONObject("main");
        float temp = BigDecimal.valueOf(res.getDouble("temp")).floatValue();
        float humidity = BigDecimal.valueOf(res.getDouble("humidity")).floatValue();
        //System.out.println("temp " + temp);
        //System.out.println("humidity " + humidity);
        Weather weather = new Weather(temp, humidity, cityName);
        return weatherRepository.save(weather);
    }

    public Weather updateWeatherByCity(String cityName)throws IOException, JSONException {
        String s = " https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=9f84de1c539b550aa161c8bde690f343";
        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext()) {

            str += scan.nextLine();
        }
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        // get the first result
        JSONObject res = obj.getJSONObject("main");
        float temp = BigDecimal.valueOf(res.getDouble("temp")).floatValue();
        float humidity = BigDecimal.valueOf(res.getDouble("humidity")).floatValue();
        //System.out.println("temp " + temp);
        //System.out.println("humidity " + humidity);
        //Weather weather = new Weather(temp, humidity, cityName);
        Weather newWeather = new Weather(temp,humidity,cityName);
        weatherRepository.findByCity(cityName).orElseThrow(() -> {
            throw new CityWeatherNotFoundException(cityName);
        });
        return weatherRepository.findByCity(cityName)
                .map(weather -> {
                    weather.setTemp(newWeather.getTemp());
                    weather.setHumidity(newWeather.getHumidity());
                    return weatherRepository.save(weather);
                })
                .orElseGet(() -> {
                    newWeather.setCity(cityName);
                    newWeather.setTemp(newWeather.getTemp());
                    newWeather.setHumidity(newWeather.getHumidity());
                    return weatherRepository.save(newWeather);
                });

    }
    public SmartWindow checkWeather(String cityName,String username){
        weatherRepository.findByCity(cityName).orElseThrow(() -> {
            throw new CityWeatherNotFoundException(cityName);
        });
        Weather weather= weatherRepository.getByCity(cityName);
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);

        if(weather.getTemp()<280 || weather.getHumidity()>60)
        {
            smartWindow.setState("closed");
            smartWindow.setAngle(0);
        }
        if(weather.getTemp()>370 || weather.getHumidity()<10)
        {
            smartWindow.setState("open");
            smartWindow.setAngle(60);
        }
         return windowRepository.save(smartWindow);

    }

}
