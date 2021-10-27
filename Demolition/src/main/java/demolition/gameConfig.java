package demolition;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;

public class gameConfig {

    private Map<String, Integer> levelTimeMap = new HashMap<String, Integer>();


    public static void main(String[] args){
        String s = "";
        try{
            File f = new File("config.json");
            Scanner scanner = new Scanner(f);
            if(scanner.hasNextLine()){
                s += scanner.nextLine();
            }
            scanner.close();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Config load failed.");
        }

    }
    
}
