package demolition;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import processing.core.PApplet;

public class Board {
    
    private List<SolidWall> solidWalls = new ArrayList<SolidWall>();
    private List<BrokenWall> brokenWalls = new ArrayList<BrokenWall>();
    private List<EmptyWall> emptyWalls = new ArrayList<EmptyWall>();
    private List<GoalTile> goalTile = new ArrayList<GoalTile>();
    boolean redEnemyTF;
    boolean yellowEnemyTF;
    private timer timerIcon;
    private redEnemy redEnemy;
    private yellowEnemy yellowEnemy;
    private Player player;

    String[][] madeBoard = new String[13][15];

    public Board(){
    }

    public String[][] makeBoard(String fileName, PApplet app) {

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

    public void map(String[][] mapBoard, PApplet app){
        redEnemyTF = false;
        yellowEnemyTF = false;
        solidWalls.clear();
        emptyWalls.clear();
        brokenWalls.clear();
        goalTile.clear();
        this.timerIcon = new timer(180, app);
        for(int i = 0; i < mapBoard.length; i++){
            for(int j = 0; j < mapBoard[0].length; j++){
                int x = j;
                int y = i;
                String mapTile = mapBoard[i][j];
                if(mapTile.equals("P")){
                    this.player = new Player(x, y, app);
                }

                if(mapTile.equals("Y")){
                    this.yellowEnemy = new yellowEnemy(x, y, app);
                    yellowEnemyTF = true;
                }

                if(mapTile.equals("R")){
                    this.redEnemy = new redEnemy(x, y, app);
                    redEnemyTF = true;
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


    public static void main(String[] args){
        /*Board board = new Board();
        board.make1stBoard();
        board.make2ndBoard();
        System.out.println(board.firstBoard[1][1]);
        System.out.println(board.secondBoard[5][1]);
        */
    }

}
