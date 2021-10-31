package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import processing.core.PFont;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    public static final int FPS = 60;
    private Player player;
    private redEnemy redEnemy;
    private yellowEnemy yellowEnemy;
    private String[][] currentBoard = new String[13][15];
    private Integer currentTimer = 0;
    List<PImage[]> yellowEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> redEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> imageList = new ArrayList<PImage[]>();
    private Map<String, String> pathTimeMap = new HashMap<String, String>();
    Boolean hasRedEnemy = false;
    Boolean hasYellowEnemy = false;
    private List<String> boardArrayName = new ArrayList<String>();
    private List<Integer> boardArrayTime = new ArrayList<Integer>();
    private int boardCounter = 0;
    private int lives;
    private timer timerIcon;
    PFont font;
    boolean keyReleased = true;
    Board board = new Board();
    public App() {
        //construct objects here
        player = board.getPlayer();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        // Load images during setup
        //Board
        font = createFont("PressStart2P-regular.ttf", 16);
        textFont(font);

        readJsonObject fileData = new readJsonObject();
        fileData.readFiles("config.json");
        this.lives = fileData.getLives();
        pathTimeMap = fileData.getPathTimeHashMap();
        //map(currentBoard);//Need to change this so that it can change boards when the goal tile is hit

        for (Map.Entry<String, String> entry : pathTimeMap.entrySet()) {
            String key = entry.getKey();
            Integer value = parseInt(entry.getValue());
            boardArrayTime.add(value);
            boardArrayName.add(key);
        }

        currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
        currentTimer =  boardArrayTime.get(boardCounter);

        board.map(currentBoard, this);
    }

    public void draw() {
        //Main loop here
        if(frameCount % 60 == 1){
            background(255, 128, 0);
           // this.timerIcon.draw(this);
            //this.timerIcon.tick();
            //text(timerIcon.getTimer(), 350, 40);
            //text(lives, 145, 40);
        }
        if(frameCount % 12 == 1){

            for(SolidWall solidWall: board.getSolidWallsList()){
                solidWall.draw(this);
            }
            for(BrokenWall brokenWall: board.getBrokenWallsList()){
                brokenWall.draw(this);
            }
            for(EmptyWall emptyWall: board.getEmptyWallsList()){
                emptyWall.draw(this);
            }
            for(GoalTile goalImage: board.getGoalTileList()){
                goalImage.draw(this);
            }
            if(hasRedEnemy == true){
                this.redEnemy.tick();
                this.redEnemy.draw(this);
            }
            if(hasYellowEnemy == true){
                this.yellowEnemy.tick();
                this.yellowEnemy.draw(this);
            }   
            this.player.tick();
            this.player.draw(this);

//Change this into a for loop to check or every GoalImage in the GoalTile Array
            for(GoalTile i: board.getGoalTileList()){
                if(this.player.getX() == i.getX() && this.player.getY() == i.getY()){

                    boardCounter ++;
                    currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
                    currentTimer =  boardArrayTime.get(boardCounter);
                    board.map(currentBoard, this);
                }
            }
            
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
            if (keyCode == DOWN && keyReleased == true) {
                player.changeOrientation(0);
                if((currentBoard[player.getY()+1][player.getX()].equals(" ")) || (currentBoard[player.getY()+1][player.getX()].equals(" "))){
                    currentBoard[player.getY()][player.getX()] = " ";
                    player.movementDOWN();
                    currentBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == UP && keyReleased == true) {
                player.changeOrientation(1);
                if((currentBoard[player.getY()-1][player.getX()].equals(" ")) || (currentBoard[player.getY()-1][player.getX()].equals("G"))){
                    currentBoard[player.getY()][player.getX()] = " ";
                    player.movementUP();
                    currentBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == RIGHT && keyReleased == true) {
                player.changeOrientation(2);
                if((currentBoard[player.getY()][player.getX()+1].equals(" ")) || (currentBoard[player.getY()][player.getX()+1].equals("G"))){
                    currentBoard[player.getY()][player.getX()] = " ";
                    player.movementRight();
                    currentBoard[player.getY()][player.getX()] = "P";
                }

            } else if (keyCode == LEFT && keyReleased == true) {
                player.changeOrientation(3);
                if((currentBoard[player.getY()][player.getX()-1].equals(" ")) || (currentBoard[player.getY()][player.getX()-1].equals(" "))){
                    currentBoard[player.getY()][player.getX()] = " ";
                    player.movementLeft();
                    currentBoard[player.getY()][player.getX()] = "P";
                }
            }
        }
        keyReleased = false;
    }

    public void keyReleased() {
        this.keyReleased = true;
    }
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
