package project.automate_me.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import project.automate_me.AutomateMeApplication;
import project.automate_me.model.*;
import project.automate_me.repository.*;

@Service
@Transactional
public class WindowService {
    WindowRepository windowRepository;
    UserRepository userRepository;
    SmokeGasLevelsRepository smokeGasLevelsRepository;
    TemperatureRepository temperatureRepository;
    DesiredTemperatureRepository desiredTemperatureRepository;
    private static final Logger log = LoggerFactory.getLogger(WindowService.class);
    public WindowService(WindowRepository windowRepository, UserRepository userRepository, SmokeGasLevelsRepository smokeGasLevelsRepository, TemperatureRepository temperatureRepository, DesiredTemperatureRepository desiredTemperatureRepository) {
        this.windowRepository = windowRepository;
        this.userRepository = userRepository;
        this.smokeGasLevelsRepository = smokeGasLevelsRepository;
        this.temperatureRepository = temperatureRepository;
        this.desiredTemperatureRepository = desiredTemperatureRepository;
    }
    public SmartWindow getByNameTest(String name){
        return windowRepository.getByName(name);
    }
    public void deleteByNameTest(String name){
        windowRepository.deleteByName(name);
    }
    public SmartWindow addWindow(SmartWindow newSmartWindow, String username) {
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = new SmartWindow(newSmartWindow.getName(), newSmartWindow.getPosition(), newSmartWindow.getState(), newSmartWindow.getAngle(), newSmartWindow.getImage(), user);
        //System.out.println(smartWindow);
        return windowRepository.save(smartWindow);
    }
    public SmartWindow checkSmokeGasLevels(String username){
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);
        SmokeGasLevels smokeGasLevels = smokeGasLevelsRepository.getById((long) 1);

        if (smokeGasLevels.getSmoke() > 20 || smokeGasLevels.getGas()>5)
        {
            smartWindow.setState("open");
            smartWindow.setAngle(90);
        }
        return windowRepository.save(smartWindow);


    }
    public SmartWindow checkRoomTemperature(String username){
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);
        String room = smartWindow.getPosition();
        Temperature temperature = temperatureRepository.getByRoom(room);

        if(temperature.getDegrees()>350)
        {
            smartWindow.setState("open");
            smartWindow.setAngle(30);
        }
        if(temperature.getDegrees()>380)
        {
            smartWindow.setState("open");
            smartWindow.setAngle(90);
        }
        if(temperature.getDegrees()<295)
        {
            smartWindow.setState("closed");
            smartWindow.setAngle(0);
        }
        return windowRepository.save(smartWindow);
    }
   public SmartWindow setWindowPosition(String username,String state,int angle){
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);
        smartWindow.setState(state);
        smartWindow.setAngle(angle);
        return windowRepository.save(smartWindow);
    }
    public SmartWindow setWindowImage(String username,String image_link){
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);
        smartWindow.setImage(image_link);
        return windowRepository.save(smartWindow);
    }
    public SelfReviewResponse selfReview(String username){
        User user = userRepository.findByUsername(username).get();
        SmartWindow smartWindow = windowRepository.getByUser(user);
        String room = smartWindow.getPosition();
        Temperature temperature = temperatureRepository.getByRoom(room);
        DesiredTemperature desiredTemperature = desiredTemperatureRepository.getByRoom(room);
        String response;
        int lowerTempAcceptable = desiredTemperature.getDegrees()-10;
        int higherTempAcceptable = desiredTemperature.getDegrees()+10;
        int currentTemp=temperature.getDegrees();
        if(currentTemp<=higherTempAcceptable &&currentTemp>=lowerTempAcceptable)
        {   response ="The Smart Window has made the "+ room +" reach the desired temperature.";

        }
        else {
            response ="The Smart Window has not made the "+ room +" reach the desired temperature.";
        }
        return new SelfReviewResponse(response);
    }

}
