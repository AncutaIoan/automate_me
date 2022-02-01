package project.automate_me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.automate_me.model.Temperature;
import project.automate_me.repository.TemperatureRepository;
import project.automate_me.utils.MqttGateway;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class TemperatureService {
    TemperatureRepository temperatureRepository;
    @Autowired
    MqttGateway mqttGateway;

    public TemperatureService(TemperatureRepository temperatureRepository, MqttGateway mqttGateway) {
        this.temperatureRepository = temperatureRepository;
        this.mqttGateway = mqttGateway;
    }

    public ResponseEntity<?> publish(String room){

        int readings=100;
            try {
                int lowerbound = 320;
                int upperbound = 365;
                while(readings>0) {
                    TimeUnit.SECONDS.sleep(2);
                    int temperatureSensor = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
                    Temperature temperature = temperatureRepository.getByRoom(room);
                    temperature.setDegrees(temperatureSensor);
                    temperatureRepository.save(temperature);
                    mqttGateway.senToMqtt(temperature.toString(), "temperatureSensor");
                    readings--;
                }
                return ResponseEntity.ok("Sending to mqtt broker.");
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.ok("Error sending to mqtt broker.");

            }


    }
}
