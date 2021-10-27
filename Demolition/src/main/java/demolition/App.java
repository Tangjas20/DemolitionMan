package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    public static final int FPS = 60;

    private Player player;
    private GoalTile GoalImage;
    private SolidWall SolidImage;
    private EmptyWall EmptyImage;
    private BrokenWall BrokenImage;
    private redEnemy redEnemy;
    private yellowEnemy yellowEnemy;
    private String[][] firstBoard = new String[13][15];
    private String[][] secondBoard = new String[13][15];
    private List<SolidWall> solidWalls = new ArrayList<SolidWall>();
    private List<BrokenWall> brokenWalls = new ArrayList<BrokenWall>();
    private List<EmptyWall> emptyWalls = new ArrayList<EmptyWall>();
    private List<GoalTile> goalTile = new ArrayList<GoalTile>();
    List<PImage[]> yellowEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> redEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> imageList = new ArrayList<PImage[]>();
    private Map<String, String> pathTimeMap = new HashMap<String, String>();
    Boolean redEnemyTF = false;
    Boolean yellowEnemyTF = false;

    public App() {
        //construct objects here
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        // Load images during setup
        //Board
        readJsonObject fileData = new readJsonObject();
        fileData.readFiles("config.json");
        Board board = new Board();
        firstBoard = board.make1stBoard();
        secondBoard = board.make2ndBoard();
        pathTimeMap = fileData.getPathTimeHashMap();
        map(firstBoard);//Need to change this so that it can change boards when the goal tile is hit
        //map(secondBoard);
    }
    
    public void map(String[][] mapBoard){
        redEnemyTF = false;
        yellowEnemyTF = false;
        for(int i = 0; i < mapBoard.length; i++){
            for(int j = 0; j < mapBoard[0].length; j++){
                int x = j;
                int y = i;
                String mapTile = mapBoard[i][j];
                if(mapTile.equals("P")){
                    this.player = new Player(x, y, this);
                }

                if(mapTile.equals("Y")){
                    this.yellowEnemy = new yellowEnemy(x, y, this);
                    yellowEnemyTF = true;
                }

                if(mapTile.equals("R")){
                    this.redEnemy = new redEnemy(x, y, this);
                    redEnemyTF = true;
                }

                if(mapTile.equals("W")){

                    this.SolidImage = new SolidWall(x, y, this.loadImage("src/main/resources/wall/solid.png"));
                    solidWalls.add(SolidImage);
                }
                else if(mapTile.equals("B")){
                    //Broken
                    this.BrokenImage = new BrokenWall(x, y, this.loadImage("src/main/resources/broken/broken.png"));
                    brokenWalls.add(BrokenImage);
                }
                else if(mapTile.equals(" ") || mapTile.equals("P") || mapTile.equals("R") || mapTile.equals("Y")){
                    //EmptyTile
                    this.EmptyImage = new EmptyWall(x, y, this.loadImage("src/main/resources/empty/empty.png"));
                    emptyWalls.add(EmptyImage);
                }
                else if(mapTile.equals("G")){
                    //GoalTile
                    this.GoalImage = new GoalTile(x, y, this.loadImage("src/main/resources/goal/goal.png"));
                    goalTile.add(GoalImage);
                }
            }
        }
    }

    public void draw() {
        //Main loop here        
        if(frameCount % 12 == 1){
            background(255, 128, 0);
            for(SolidWall i: solidWalls){
                this.SolidImage = i;
                this.SolidImage.draw(this);
            }
            for(BrokenWall i: brokenWalls){
                this.BrokenImage = i;
                this.BrokenImage.draw(this);
            }
            for(EmptyWall i: emptyWalls){
                this.EmptyImage = i;
                this.EmptyImage.draw(this);
            }
            for(GoalTile i: goalTile){
                this.GoalImage = i;
                this.GoalImage.draw(this);
            }
            if(redEnemyTF == true){
                this.redEnemy.tick();
                this.redEnemy.draw(this);
            }
            if(yellowEnemyTF == true){
                this.yellowEnemy.tick();
                this.yellowEnemy.draw(this);
            }   
            this.player.tick();
            this.player.draw(this);
            
        }
    }

    public void keyPressed() {
        /*
            .get(0) corresponds with base player
            .get(1) corresponds with up player
            .get(2) corresponds with right player
            .get(3) corresponds with left player
        */
        if (key == CODED) {
            if (keyCode == DOWN) {
                player.changeOrientation(0);
                if(firstBoard[player.getY()+1][player.getX()].equals(" ")){
                    firstBoard[player.getY()][player.getX()] = " ";
                    player.movementDOWN();
                    firstBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == UP) {
                player.changeOrientation(1);
                if(firstBoard[player.getY()-1][player.getX()].equals(" ")){
                    firstBoard[player.getY()][player.getX()] = " ";
                    player.movementUP();
                    firstBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == RIGHT) {
                player.changeOrientation(2);
                if(firstBoard[player.getY()][player.getX()+1].equals(" ")){
                    firstBoard[player.getY()][player.getX()] = " ";
                    player.movementRight();
                    firstBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == LEFT) {
                player.changeOrientation(3);
                if(firstBoard[player.getY()][player.getX()-1].equals(" ")){
                    firstBoard[player.getY()][player.getX()] = " ";
                    player.movementLeft();
                    firstBoard[player.getY()][player.getX()] = "P";
                }
            }
        }
    }

    
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
