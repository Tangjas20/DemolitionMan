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
    private String[][] currentBoard = new String[13][15];
    private Integer currentTimer = 0;
    List<PImage[]> yellowEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> redEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> imageList = new ArrayList<PImage[]>();
    private Map<String, String> pathTimeMap = new HashMap<String, String>();
    private List<String> boardArrayName = new ArrayList<String>();
    private List<Integer> boardArrayTime = new ArrayList<Integer>();
    private int boardCounter = 0;
    PFont font;
    boolean keyReleased = true;
    Board board;
    private int lives;
    private timer timerIcon;
    boolean drewPlayer = false;
    private List<Bomb> bombList = new ArrayList<Bomb>();

    public App() {
        //construct objects here
        board = new Board();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void resetGame(){
        currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
        currentTimer =  boardArrayTime.get(boardCounter);
        board.map(currentBoard, this);
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
        

        for (Map.Entry<String, String> entry : pathTimeMap.entrySet()) {
            String key = entry.getKey();
            Integer value = parseInt(entry.getValue());
            boardArrayTime.add(value);
            boardArrayName.add(key);
        }

        currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
        currentTimer =  boardArrayTime.get(boardCounter);

        board.map(currentBoard, this);
        this.timerIcon = new timer(currentTimer, this);
    }

    public void draw() {
        //Main loop here
        drewPlayer = false;
        checkGameState();
        if(frameCount % 60 == 1){
            background(255, 128, 0);
           this.timerIcon.draw(this);
            this.timerIcon.tick();
            text(timerIcon.getTimer(), 350, 40);
            text(lives, 145, 40);
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
            
            for(Bomb bomb: bombList){
                bomb.draw(this);
            }

            
            if(board.redEnemyTF == true){
                if((board.getPlayer().getX() == board.getRedEnemy().getX()) && (board.getPlayer().getY() == (board.getRedEnemy().getY()-1))){
                    board.getPlayer().tick();
                    board.getPlayer().draw(this);
                    drewPlayer = true;
                }
                board.getRedEnemy().tick();
                board.getRedEnemy().draw(this);

            }

            if(board.yellowEnemyTF == true){
                if((board.getPlayer().getX() == board.getYellowEnemy().getX()) && (board.getPlayer().getY() == (board.getYellowEnemy().getY()-1))){
                    board.getPlayer().tick();
                    board.getPlayer().draw(this);
                    drewPlayer = true;
                }
                board.getYellowEnemy().tick();
                board.getYellowEnemy().draw(this);
            }

            if(drewPlayer == false){
                board.getPlayer().tick();
                board.getPlayer().draw(this);
            }


//Change this into a for loop to check or every GoalImage in the GoalTile Array
            for(GoalTile i: board.getGoalTileList()){
                if(board.getPlayer().getX() == i.getX() && board.getPlayer().getY() == i.getY()){

                    boardCounter ++;
                    resetGame();
                    this.timerIcon = new timer(currentTimer, this);
                }
            }
            
        }
        for(Bomb i: bombList){
            i.draw(this);
        }
    }

    public void checkGameState(){
        if((board.getPlayer().getX() == board.getRedEnemy().getX() && board.getPlayer().getY() == board.getRedEnemy().getY())
        || (board.getPlayer().getX() == board.getYellowEnemy().getX() && board.getPlayer().getY() == board.getYellowEnemy().getY())){
            resetGame();
            lives--;
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
            if (keyCode == TAB && keyReleased == true) {
                Bomb b = new Bomb(board.getPlayer().getX(), board.getPlayer().getY(), this, currentBoard);
                bombList.add(b);
                currentBoard[board.getPlayer().getX()][board.getPlayer().getY()] = "D";
            }
            else if(keyCode == DOWN && keyReleased == true) {
                board.getPlayer().changeOrientation(0);
                if((currentBoard[board.getPlayer().getY()+1][board.getPlayer().getX()].matches(" |R|Y")) || (currentBoard[board.getPlayer().getY()+1][board.getPlayer().getX()].equals(" "))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementDOWN();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == UP && keyReleased == true) {
                board.getPlayer().changeOrientation(1);
                if((currentBoard[board.getPlayer().getY()-1][board.getPlayer().getX()].matches(" |R|Y")) || (currentBoard[board.getPlayer().getY()-1][board.getPlayer().getX()].equals("G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementUP();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == RIGHT && keyReleased == true) {
                board.getPlayer().changeOrientation(2);
                if((currentBoard[board.getPlayer().getY()][board.getPlayer().getX()+1].matches(" |R|Y")) || (currentBoard[board.getPlayer().getY()][board.getPlayer().getX()+1].equals("G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementRight();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == LEFT && keyReleased == true) {
                board.getPlayer().changeOrientation(3);
                if((currentBoard[board.getPlayer().getY()][board.getPlayer().getX()-1].matches(" |R|Y")) || (currentBoard[board.getPlayer().getY()][board.getPlayer().getX()-1].equals(" "))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementLeft();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }
            }
        }
        keyReleased = false;
    }

    public void keyReleased() {
        this.keyReleased = true;
    }

    public String[][] getCurrentBoard(){
        return currentBoard;
    }
    
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
