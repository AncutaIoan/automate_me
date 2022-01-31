package project.automate_me.controller;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTempController {

    public void selfreview(String zone, float bench) //primeste ca parametrii camera pentru care se doreste sa se face self-review-ul si temperatura reper
    {
        JSONParser parser = new JSONParser(); //pentru parcurgere
        JSONObject jsoncreat = new JSONObject(); //pentru self-review
        try
        {
            Object obj = parser.parse(new FileReader("tempcur.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map temps = ((Map)jsonObject.get("temperaturi"));
            Iterator<Map.Entry> itr1 = temps.entrySet().iterator();
            while(itr1.hasNext()){
                Map.Entry pair = itr1.next();
                if(zone==(String)pair.getKey()){
                    if((float)pair.getValue()-bench<2)
                        jsoncreat.put(pair.getKey(), "verde");
                    else if ((float)pair.getValue()-bench<5 && (float)pair.getValue()-bench >=2)
                        jsoncreat.put(pair.getKey(), "galben");
                    else
                        jsoncreat.put(pair.getKey(), "rosu");

                    try{
                        FileWriter file = new FileWriter("selfreview.json");
                        file.write(jsonObject.toJSONString());
                        file.close();
                    } catch(IOException a){
                        a.printStackTrace();
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public float roomtempaverage(String zone) //functie care intoarce temperatura medie intr-o camera data ca parametru
    {
        JSONParser parser = new JSONParser();
        float average_temps = 0;
        try
        {
            Object obj = parser.parse(new FileReader("tempavg.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map temps = ((Map)jsonObject.get(zone));
            Iterator<Map.Entry> itr1 = temps.entrySet().iterator();
            while(itr1.hasNext()){
                Map.Entry pair = itr1.next();
                average_temps += (float)pair.getValue();
            }
            return average_temps/5;
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

