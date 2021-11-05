package demolition;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import processing.core.PApplet;

/**
* Sets up the backend of the board in a String[][] format which stores the location of all tiles and enemies.
* Contains the methods which can set up the board, clear board and add entities to String[][].
 */
public class Board {
    
    private List<SolidWall> solidWalls = new ArrayList<SolidWall>();
    private List<BrokenWall> brokenWalls = new ArrayList<BrokenWall>();
    private List<EmptyWall> emptyWalls = new ArrayList<EmptyWall>();
    private List<GoalTile> goalTile = new ArrayList<GoalTile>();
    boolean redEnemyTF;
    boolean yellowEnemyTF;
    private redEnemy redEnemy;
    private yellowEnemy yellowEnemy;
    private Player player;
    List<redEnemy> redEnemyList = new ArrayList<>();
    List<yellowEnemy> yellowEnemyList = new ArrayList<>();

    String[][] madeBoard = new String[13][15];

    public Board(){
    }

    /**
    * Reads the file taken from the config.json and converts it to a String[][] which will be used to store the current board. 
    * Returns a String[13][15] from the inputted txt file
    * @param fileName is the name of the levels file which is found in the config.json in the root directory.
    */
    public String[][] makeBoard(String fileName, PApplet app) {//Returns a String[][] given a Json object

        try{
            File firstFile = new File(fileName);
            Scanner scanner = new Scanner(firstFile);
            int i = 0;

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                madeBoard[i] = data.split("(?!^)");
                i++;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("No such file.");
        }
        return madeBoard;
    }

    /**
    * A makeBoard method which is used for testing purposes. Takes files from the src/test/resources folder and returns a String[13][15]
    * @param fileName is the name of the levels file which is the test config.json.
     */
    public String[][] testMakeBoard(String fileName, PApplet app) {

        try{
            File firstFile = new File("src/test/resources/" + fileName);
            Scanner scanner = new Scanner(firstFile);
            int i = 0;

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                madeBoard[i] = data.split("(?!^)");
                i++;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("No such file.");
        }
        return madeBoard;
    }

    /**
    * Creates Player, redEnemy, yellowEnemy, and tile blocks based on the String[][] board created in the makeBoard method and adds them to their respective Lists in the board attributes.
    * @param mapBoard is the currentBoard state which is passed through so that the object class Lists can be updated accordingly.
    */
    public void map(String[][] mapBoard, PApplet app){//Creates map based on the String[][]
        redEnemyTF = false;
        yellowEnemyTF = false;
        solidWalls.clear();
        emptyWalls.clear();
        brokenWalls.clear();
        goalTile.clear();

        for(int j = 0; j < mapBoard[0].length; j++){
            for(int i = 0; i < mapBoard.length; i++){
                int x = j;
                int y = i;
                String mapTile = mapBoard[i][j];
                if(mapTile.equals("P")){
                    this.player = new Player(x, y, app);
                }

                if(mapTile.equals("Y")){
                    this.yellowEnemy = new yellowEnemy(x, y, app);
                    yellowEnemyTF = true;
                    yellowEnemyList.add(yellowEnemy);
                    
                }

                if(mapTile.equals("R")){
                    this.redEnemy = new redEnemy(x, y, app);
                    redEnemyTF = true;
                    redEnemyList.add(redEnemy);
                }

                if(mapTile.equals("W")){
                    SolidWall SolidImage = new SolidWall(x, y, app);
                    solidWalls.add(SolidImage);
                }

                else if(mapTile.equals("B")){
                    //Broken
                    BrokenWall BrokenImage = new BrokenWall(x, y, app);
                    brokenWalls.add(BrokenImage);
                }
                else if(mapTile.equals(" ") || mapTile.equals("P") || mapTile.equals("R") || mapTile.equals("Y")){
                    //EmptyTile
                    EmptyWall EmptyImage = new EmptyWall(x, y, app);
                    emptyWalls.add(EmptyImage);
                }
                else if(mapTile.equals("G")){
                    //GoalTile
                    GoalTile GoalImage = new GoalTile(x, y, app);
                    goalTile.add(GoalImage);
                }
            }
        }
    }

    /**
    * Breaks the necessary blocks when an explosion occurs by clearing all objects in the emptyWalls and brokenWalls lists and re-adding them after scanning through the newly formed current board
    * @param mapBoard is the currentBoard state of the App and is passed through so the lists containing the objects can be updated by clearing then re-adding.
    */
    public void explosionBreakBlock(String[][] mapBoard, PApplet app){//After explosion this resets empty and broken walls so that it can be redrawn
        emptyWalls.clear();
        brokenWalls.clear();

        for(int j = 0; j < mapBoard[0].length; j++){
            for(int i = 0; i < mapBoard.length; i++){
                int x = j;
                int y = i;
                String mapTile = mapBoard[i][j];
                if(mapTile.equals("B")){
                    //Broken
                    BrokenWall BrokenImage = new BrokenWall(x, y, app);
                    brokenWalls.add(BrokenImage);
                }
                else if(mapTile.equals(" ") || mapTile.equals("P") || mapTile.equals("R") || mapTile.equals("Y")){
                    //EmptyTile
                    EmptyWall EmptyImage = new EmptyWall(x, y, app);
                    emptyWalls.add(EmptyImage);
                }
            }
        }
    }
    
    public List<SolidWall> getSolidWallsList(){
        return this.solidWalls;
    }

    public List<BrokenWall> getBrokenWallsList(){
        return this.brokenWalls;
    }

    public List<EmptyWall> getEmptyWallsList(){
        return this.emptyWalls;
    }

    public List<GoalTile> getGoalTileList(){
        return this.goalTile;
    }

    public Player getPlayer(){
        return this.player;
    }

    public redEnemy getRedEnemy(){
        return this.redEnemy;
    }

    public yellowEnemy getYellowEnemy(){
        return this.yellowEnemy;
    }



}
