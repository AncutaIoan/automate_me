package com.example.demo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Room_tempController {

    @GetMapping("/room_temp")
    public float room_temp(@RequestParam(value="zone", defaultValue="bucatarie"), String zone)
    {
        JSONParser parser = new JSONParser();
        float average_temps = 0;
        try
        {
            Object obj = parser.parse(new FileReader("temp.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map temps = ((Map)jsonObject.get(zone));
            Iterator<Map.Entry> itr1 = temps.entrySet().iterator();
            while(itr1.hasNext()){
                Map.Entry pair = itr1.next();
                average_temps += (float)pair.getValue();
            }
            return (float)average_temps/5;
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

