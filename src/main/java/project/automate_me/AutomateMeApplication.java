package project.automate_me;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.hibernate.annotations.common.util.impl.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication()
public class AutomateMeApplication {
    private static final Logger log = LoggerFactory.getLogger(AutomateMeApplication.class);
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AutomateMeApplication.class, args);

    }



}
