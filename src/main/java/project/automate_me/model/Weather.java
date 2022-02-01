package project.automate_me.model;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Weather{

    private @Id @GeneratedValue Long id;
    private float temp;
    private float humidity;
    private String city;
    public Weather() {
    }

    public Weather(float temp, float humidity,String city) {

        this.temp=temp;
        this.humidity=humidity;
        this.city=city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }


}