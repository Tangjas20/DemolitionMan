package demolition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.io.File;
import processing.data.JSONObject;

/**
* Class which will read from a JSON file and extract information based on levels, lives and time and put values into a HashMap which will be used for map loading. Contains attributes such as the String fileRead, numberOflevels, lives and pathTimeMap.
*/
public class readJsonObject {
    String fileRead = "";
    int numberOfLevels = 0;
    private int lives;
    private Map<String, String> pathTimeMap = new LinkedHashMap<String, String>();

    /**
    * Takes in a filePath String parameter and uses a scanner to append each Line to the fileRead variable. If filepath is invalid an exception of Config Load failed error will be printed. 
    * Extracts all info relating to lives, path and time and stores it in variables to be used in the app.
    * @param filePath is the path of the config.json located in the root directory.
    */
    public void readFiles(String filePath){
        try{
            File f = new File(filePath);
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