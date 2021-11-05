package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import processing.core.PFont;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
* This serves as the core manager for the game and functionality which calls upon other classes and extends PApplet.
*/
public class App extends PApplet {
    public static final int FPS = 60;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    int counter = 0;
    String[][] currentBoard;
    Integer currentTimer = 0;
    private Map<String, String> pathTimeMap;
    private List<String> boardArrayName;
    private List<Integer> boardArrayTime;
    private int boardCounter = 0;
    PFont font;
    boolean keyReleased = true;
    Board board;
    private int lives;
    timer timerIcon;
    boolean drewPlayer = false;
    List<Bomb> bombList;
    boolean gameOver = false;
    boolean isTest = false;
    readJsonObject fileData;
    boolean gameReset = false;

    /**
    *Constructor which will initialise the board, bombList, boardArrayTime, boardArrayName, pathTimeMap, and currentBoard which are used in the game.
     */
    public App() {
        //construct objects here
        board = new Board();
        bombList = new ArrayList<Bomb>();
        boardArrayTime = new ArrayList<Integer>();
        boardArrayName = new ArrayList<String>();
        pathTimeMap = new HashMap<String, String>();
        currentBoard = new String[13][15];
    }
    /**
    *Sets the size of the game window
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /**
    * Resets the game to the original board by clearing out red and yellow enemy lists, as well as redrawing board from text file and resetting timer.
     */
    public void resetGame(){//Method to reset game to original board.
        board.redEnemyList.clear();
        board.yellowEnemyList.clear();
        currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
        currentTimer =  boardArrayTime.get(boardCounter);
        board.map(currentBoard, this);
        this.timerIcon = new timer(currentTimer, this);
        gameReset = false;
    }
    /**
    * Calls upon the readJsonObject class to read text input from a JSONObject, extracting the lives data and time data. The data is stored in a HashMap named pathTimeMap. 
    * If isTest is true, then the program will read the config file from the src/test/resources/ folder
     */
    public void setup() {
        frameRate(FPS);
        font = createFont("PressStart2P-regular.ttf", 16);
        fill(0);
        textFont(font);
        
        if(isTest == false){
            readJsonObject fileData = new readJsonObject(); 
            fileData.readFiles("config.json");
            this.lives = fileData.getLives();
            pathTimeMap = fileData.getPathTimeHashMap(); //Stores the level name and time
        }
        else{
            readJsonObject fileData = new readJsonObject(); 
            fileData.readFiles("src/test/resources/testConfig.json");
            this.lives = fileData.getLives();
            pathTimeMap = fileData.getPathTimeHashMap(); //Stores the level name and time
        }   //Reads config file for level, time and lives info
       
        
        for (Map.Entry<String, String> entry : pathTimeMap.entrySet()) {
            String key = entry.getKey();
            Integer value = parseInt(entry.getValue());
            boardArrayTime.add(value);
            boardArrayName.add(key);
        }

        if(isTest == false){//Calls upon config file in the root directory if false
            currentBoard = board.makeBoard(boardArrayName.get(boardCounter), this);
            currentTimer =  boardArrayTime.get(boardCounter);
            board.map(currentBoard, this);
            this.timerIcon = new timer(currentTimer, this);
        }
        else if(isTest == true){//This is used for testing purposes
            currentBoard = board.testMakeBoard(boardArrayName.get(boardCounter), this);
            currentTimer =  boardArrayTime.get(boardCounter);
            board.map(currentBoard, this);
            this.timerIcon = new timer(currentTimer, this);   
        }
    }
    /**
    * Combines the methods and logic from the other classes to build the core game as well as render in graphics. This method repeats at 60 FPS and will check the game state, draw background, players, enemies and other entties.
    * It will check for certain conditions to be met and act accordingly. These include checking if bomb explosions have finished and removing them from the bombList, checking if the player has been killed etc.
    */
    public void draw() {
        if (lives > 0 && this.timerIcon.getTimer() > 0 && gameOver == false){//Tests to see if conditions are met for game to continue
            drewPlayer = false;
            checkGameState();//Checks if player touches enemy
            background(255, 128, 0);
            this.timerIcon.draw(this);
            this.timerIcon.tick();
            text(timerIcon.getTimer(), 350, 40);

            if (frameCount % 60 == 0){//Every 60frames/every second calls the enemies to move
                if (board.yellowEnemyTF){
                    for(yellowEnemy i: board.yellowEnemyList)
                        moveYellowEnemy(i);
                }
                if (board.redEnemyTF)
                    for(redEnemy i: board.redEnemyList)
                        moveRedEnemyAI(i);
            }

            text(lives, 145, 40);
            for (EmptyWall emptyWall: board.getEmptyWallsList()){
                emptyWall.draw(this);
            }
            for (SolidWall solidWall: board.getSolidWallsList()){
                solidWall.draw(this);
            }
            for (BrokenWall brokenWall: board.getBrokenWallsList()){
                brokenWall.draw(this);
            }
            for (GoalTile goalImage: board.getGoalTileList()){
                goalImage.draw(this);
            }

            for (Bomb bomb: bombList){//Checks all the bombs and their states, and explodes 
                if (bomb.getIsAlive()){
                    bomb.draw(this);
                    counter = 0;
                }
                else if (!bomb.getExplosionFinished()) {
                    bomb.drawExplosion(this);
                    
                    currentBoard = bomb.getBoard();
                    if (counter == 0){
                        counter++;
                        bomb.explosion(this);
                        board.explosionBreakBlock(currentBoard, this);
                    }

                if (bomb.killedPlayer ){
                    resetGame();
                    lives--;
                    bomb.killedPlayer = false;
                    bomb.explosionFinished= true;
                    for (Bomb bombDefuse: bombList){
                        bombDefuse.explosionFinished = true;
                    }
                    break;
                }
            }
                    
        }
            bombList.removeIf(b -> b.getExplosionFinished() == true);//Redundant bombs will be removed from bombsList

            if (board.redEnemyTF == true){//If enemy exists
                for(redEnemy i: board.redEnemyList){//If enemy is below player, they will be drawn last
                    if ((board.getPlayer().getX() == i.getX()) && (board.getPlayer().getY() == (i.getY()-1)) && drewPlayer == false){
                        board.getPlayer().tick();
                        board.getPlayer().draw(this);
                        drewPlayer = true;
                }
                    if(i.isEnemyDead == false){
                        i.tick();
                        i.draw(this);
                    }
                }

            }

            if (board.yellowEnemyTF == true){//Same logic as above
                for(yellowEnemy i: board.yellowEnemyList){
                    if ((board.getPlayer().getX() == i.getX()) && (board.getPlayer().getY() == (i.getY()-1)) && drewPlayer == false){
                        board.getPlayer().tick();
                        board.getPlayer().draw(this);
                        drewPlayer = true;
                    }
                 
                    if(i.isEnemyDead == false){
                        i.tick();
                        i.draw(this);
                    }
                }
            }

            if (drewPlayer == false){//If player still hasn't been drawn
                board.getPlayer().tick();
                board.getPlayer().draw(this);
            }

    //Change this into a for loop to check or every GoalImage in the GoalTile Array
            for (GoalTile i: board.getGoalTileList()){
                if (board.getPlayer().getX() == i.getX() && board.getPlayer().getY() == i.getY()){
                    if(boardCounter == 0 && pathTimeMap.size() == 1){
                        background(255, 128, 0);
                        text("YOU WIN!", 180, 230);
                        gameOver = true;
                    }
                    else if (boardCounter < pathTimeMap.size()-1){
                        boardCounter ++;
                        resetGame();
                        bombList.clear();
                        this.timerIcon = new timer(currentTimer, this);
                    } else {
                        gameOver = true;
                    }
                }
            }
        } 
        else if (gameOver == true) {//If the final goal tile has been passed
            background(255, 128, 0);
            text("YOU WIN!", 180, 230);
        }
        else {//If losing conditions are met, print game over sreen
            background(255, 128, 0);
            text("GAME OVER!", 180, 230);
        }
    }
    /**
    * Checks whether the red or yellow enemies are on the same tile as the player. If true, the game will reset and the player will lose 1 life.
    */
    public void checkGameState(){//Checks if enemy and player is on the same tile, if so, reset
        if(board.redEnemyTF){
            for(redEnemy i: board.redEnemyList){
                if((board.getPlayer().getX() == i.getX()) && (board.getPlayer().getY() == i.getY()) && i.isEnemyDead == false){
                    gameReset = true;
                    lives--;
                }
            }
        }

        if(board.yellowEnemyTF){
            for(yellowEnemy i: board.yellowEnemyList){
                if((board.getPlayer().getX() == i.getX()) && (board.getPlayer().getY() == i.getY()) && i.isEnemyDead == false){
                    gameReset = true;
                    lives--;
            }
        }
        }
        if(gameReset == true){
            resetGame();
        }
    }
    /**
    * Determines if a significant key has been pressed and will allow the player to act accordingly. 
    * The significant keys are space, left, right, up and down.
    */
    public void keyPressed() {//Checks if any keystrokes are pressed and moves characters accordingly.
        /*
            .get(0) corresponds with base player
            .get(1) corresponds with up player
            .get(2) corresponds with right player
            .get(3) corresponds with left player
        */
        if (keyCode == 32 && this.keyReleased == true) {
            Bomb b = new Bomb(board.getPlayer().getX(), board.getPlayer().getY(), this, currentBoard);
            bombList.add(b);
            keyReleased = false;
        }
            if(keyCode == 40 && keyReleased == true) {
                board.getPlayer().changeOrientation(0);
                if((currentBoard[board.getPlayer().getY()+1][board.getPlayer().getX()].matches(" |R|Y|G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementDOWN();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == UP && keyReleased == true) {
                board.getPlayer().changeOrientation(1);
                if((currentBoard[board.getPlayer().getY()-1][board.getPlayer().getX()].matches(" |R|Y|G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementUP();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == RIGHT && keyReleased == true) {
                board.getPlayer().changeOrientation(2);
                if((currentBoard[board.getPlayer().getY()][board.getPlayer().getX()+1].matches(" |R|Y|G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementRight();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }

            } else if (keyCode == LEFT && keyReleased == true) {
                board.getPlayer().changeOrientation(3);
                if((currentBoard[board.getPlayer().getY()][board.getPlayer().getX()-1].matches(" |R|Y|G"))){
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = " ";
                    board.getPlayer().movementLeft();
                    currentBoard[board.getPlayer().getY()][board.getPlayer().getX()] = "P";
                }
            }
        keyReleased = false;
    }
   
    /*
        .get(0) corresponds with base player
        .get(1) corresponds with up player
        .get(2) corresponds with right player
        .get(3) corresponds with left player
    */
    //Yellow enemy simply moves based on their orientation and whether there is a wall or not in front
    /**
    * Moves yellow enemy in a set pattern that is clockwise. 
    * The yellow enemy will change orientation when a wall has been hit.
    */
    public void moveYellowEnemy(yellowEnemy i){
        if(i.orientation == 0){
            if(currentBoard[i.getY()+1][i.getX()].matches(" |R|Y|G|P")){
                i.movementDOWN();
                currentBoard[i.getY()][i.getX()] = "Y";
                currentBoard[i.getY()-1][i.getX()] = " ";
            }
            else{i.changeOrientation(3);}
        }

        else if(i.orientation == 1){
            if(currentBoard[i.getY()-1][i.getX()].matches(" |R|Y|G|P")){
                i.movementUP();
                currentBoard[i.getY()][i.getX()] = "Y";
                currentBoard[i.getY()+1][i.getX()] = " ";
            }
            else{i.changeOrientation(2);}
        }

        else if(i.orientation == 2){
            if(currentBoard[i.getY()][i.getX()+1].matches(" |R|Y|G|P")){
                i.movementRight();
                currentBoard[i.getY()][i.getX()] = "Y";
                currentBoard[i.getY()][i.getX()-1] = " ";
            }
            else{i.changeOrientation(0);}
        }

        else if(i.orientation == 3){
            if(currentBoard[i.getY()][i.getX()-1].matches(" |R|Y|G|P")){
                i.movementLeft();
                currentBoard[i.getY()][i.getX()] = "Y";
                currentBoard[i.getY()][i.getX()+1] = " ";
            }
            else{i.changeOrientation(1);}
        }
    }
        /*
            .get(0) corresponds with base player
            .get(1) corresponds with up player
            .get(2) corresponds with right player
            .get(3) corresponds with left player
        */
    public void moveRedEnemyAI(redEnemy i){//Moves with the random library, but will first calculate the possible unblocked paths
        int[] possiblePath = {0, 0, 0, 0};
        boolean deadEnd = false;
         if(i.orientation == 0){
            if(currentBoard[i.getY()+1][i.getX()].matches(" |R|Y|G|P")){
                i.movementDOWN();
                currentBoard[i.getY()][i.getX()] = "R";
                currentBoard[i.getY()-1][i.getX()] = " ";
            }
            else{
                deadEnd = true;
            }
        } else if (i.orientation == 1){
            if(currentBoard[i.getY()-1][i.getX()].matches(" |R|Y|G|P")){
                i.movementUP();
                currentBoard[i.getY()][i.getX()] = "R";
                currentBoard[i.getY()+1][i.getX()] = " ";

            } else {
                deadEnd = true;
            }
        }

        else if(i.orientation == 2){
            if(currentBoard[i.getY()][i.getX()+1].matches(" |R|Y|G|P")){
                i.movementRight();
                currentBoard[i.getY()][i.getX()] = "R";
                currentBoard[i.getY()][i.getX()-1] = " ";
            }
            else{
                deadEnd = true;
            }
        }

        else if(i.orientation == 3){
            if(currentBoard[i.getY()][i.getX()-1].matches(" |R|Y|G|P")){
                i.movementLeft();
                currentBoard[i.getY()][i.getX()] = "R";
                currentBoard[i.getY()][i.getX()+1] = " ";
            }
            else{
                deadEnd = true;
            }
        }
        if(deadEnd == true){
            List<Integer> positions = new ArrayList<>();
            if(currentBoard[i.getY()+1][i.getX()].matches(" |R|Y|G|P"))
                positions.add(0);
            if(currentBoard[i.getY()-1][i.getX()].matches(" |R|Y|G|P"))
                positions.add(1);
            if(currentBoard[i.getY()][i.getX()+1].matches(" |R|Y|G|P"))
                positions.add(2);
            if(currentBoard[i.getY()][i.getX()-1].matches(" |R|Y|G|P"))
                positions.add(3); 

            Random r = new Random();
            int randomNumber = r.nextInt(positions.size());
            i.changeOrientation(positions.get(randomNumber));
        }
    }

    /**
    * Changes the value of boolean keyReleased to true once a key has been let go.
    */
    public void keyReleased() {
        this.keyReleased = true;
    }

    public int getLives(){
        return this.lives;
    }

    public int getCurrentTimer(){
        return this.currentTimer;
    }

    public List<Bomb> getBombList(){
        return this.bombList;
    }

    public void changeLives(int lives){
        this.lives = lives;
    }

    public int getBoardCount(){
        return this.boardCounter;
    }

    public String[][] getCurrentBoard(){
        return this.currentBoard;
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
