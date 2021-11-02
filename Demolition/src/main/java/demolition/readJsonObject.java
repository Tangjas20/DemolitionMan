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
            //System.out.println(fileRead);
        } catch (Exception e) {
            System.out.println("Config load failed.");
        }
        JSONObject jsn = JSONObject.parse(fileRead);


       //System.out.println(jsn.keys());
       //System.out.println(jsn.getJSONArray("levels").getJSONObject(0));
        //JSONObject jsn1 = jsn.getJSONArray("levels").getJSONObject(0);
        //System.out.println(jsn1.get("path"));
        //numberOfLevels = jsn.getJSONArray("levels").size();
        //System.out.println(numberOfLevels);
       // System.out.println(jsn1);
       numberOfLevels = jsn.getJSONArray("levels").size();
       //System.out.println(numberOfLevels);
        //System.out.println(jsn1);
        
        for(int i = 0; i < numberOfLevels; i++){
            JSONObject jsn1 = jsn.getJSONArray("levels").getJSONObject(i);
            lives = jsn.getInt("lives");
            pathTimeMap.put(jsn1.getString("path"), jsn1.get("time").toString());
           //System.out.println(jsn1.get("path"));
           // System.out.println(jsn1.get("time"));
        }


       /* System.out.println(pathTimeMap.get("level1.txt"));
        for (String key : pathTimeMap.keySet()) {
            System.out.println(key);
        }
        */
        //System.out.println(jsn1.get("path"));
       //System.out.println(jsn.get("lives"));
       //JSONObject jsn1 = JSONObject.parse(jsn.get("levels"));

       //json.getJSONArray("content").getJSONObject(0).getString("article")
    }

    public Map<String, String> getPathTimeHashMap() {
        return this.pathTimeMap;
    }

    public int getLives() {
        return this.lives;
    }
 
}