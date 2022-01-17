package com.example.demo;

public class Weather {

    private final long id;
    private final int number_of_degrees;
    private final int wind_speed;

    public Weather(long id, int number_of_degrees, int wind_speed) {
        this.id = id;
        this.number_of_degrees = number_of_degrees;
        this.wind_speed = wind_speed;
    }

    public long getId() {
        return id;
    }

    public int getNumber_of_degrees() {
        return number_of_degrees;
    }

    public int getWind_speed() {
        return wind_speed;
    }


}
