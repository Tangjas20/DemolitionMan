package demolition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.io.File;
import processing.data.JSONObject;

public class readJsonObject {
    String fileRead = "";
    int numberOfLevels = 0;
    private int lives;
    private Map<String, String> pathTimeMap = new LinkedHashMap<String, String>();

    public void readFiles(){
        try{
            File f = new File("config.json");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()){
                
                fileRead += scanner.nextLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Config load failed.");
        }
        JSONObject jsn = JSONObject.parse(fileRead);
       numberOfLevels = jsn.getJSONArray("levels").size();
        
        for (int i = 0; i < numberOfLevels; i++){
            JSONObject jsn1 = jsn.getJSONArray("levels").getJSONObject(i);
            lives = jsn.getInt("lives");
            pathTimeMap.put(jsn1.getString("path"), jsn1.get("time").toString());
        }
    }

    public Map<String, String> getPathTimeHashMap() {
        return this.pathTimeMap;
    }

    public int getLives() {
        return this.lives;
    }
 
}