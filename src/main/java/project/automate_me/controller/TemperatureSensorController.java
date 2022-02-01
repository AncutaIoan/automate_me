package project.automate_me.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.automate_me.service.TemperatureService;

@RestController
public class TemperatureSensorController {

    TemperatureService temperatureService;

    public TemperatureSensorController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/temperatureSensor/{room}")
    public ResponseEntity<?> temperatureSensor(@PathVariable String room){

        return temperatureService.publish(room);

    }
}
