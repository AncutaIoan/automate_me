package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WeatherController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/weather")
    public Weather weather(@RequestParam(value="zone",defaultValue ="kitchen") String zone)
    {
        if(zone=="bucatarie"){
            return new Weather(counter.incrementAndGet(),20,30 );
        }
        else{
            if(zone=="dormitor"){
                return new Weather(counter.incrementAndGet(),25,20 );
            }
            else{
                return new Weather(counter.incrementAndGet(),23,22 );
            }
        }
    }
}
