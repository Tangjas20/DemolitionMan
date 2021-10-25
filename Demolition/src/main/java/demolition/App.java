package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;

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
    private List<yellowEnemy> yellowList = new ArrayList<yellowEnemy>();
    private List<BrokenWall> brokenWalls = new ArrayList<BrokenWall>();
    private List<EmptyWall> emptyWalls = new ArrayList<EmptyWall>();
    private List<GoalTile> goalTile = new ArrayList<GoalTile>();
    List<PImage[]> yellowEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> redEnemyImageList = new ArrayList<PImage[]>();
    List<PImage[]> imageList = new ArrayList<PImage[]>();
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
        PImage[] playerGif = new PImage[4];
        playerGif[0] = loadImage("src/main/resources/player/player1.png");
        playerGif[1] = loadImage("src/main/resources/player/player2.png");
        playerGif[2] = loadImage("src/main/resources/player/player3.png");
        playerGif[3] = loadImage("src/main/resources/player/player4.png");

        PImage[] playerUpGif = new PImage[4];
        playerUpGif[0] = loadImage("src/main/resources/player/player_up1.png");
        playerUpGif[1] = loadImage("src/main/resources/player/player_up2.png");
        playerUpGif[2] = loadImage("src/main/resources/player/player_up3.png");
        playerUpGif[3] = loadImage("src/main/resources/player/player_up4.png");

        PImage[] playerRightGif = new PImage[4];
        playerRightGif[0] = loadImage("src/main/resources/player/player_right1.png");
        playerRightGif[1] = loadImage("src/main/resources/player/player_right2.png");
        playerRightGif[2] = loadImage("src/main/resources/player/player_right3.png");
        playerRightGif[3] = loadImage("src/main/resources/player/player_right4.png");

        PImage[] playerLeftGif = new PImage[4];
        playerLeftGif[0] = loadImage("src/main/resources/player/player_left1.png");
        playerLeftGif[1] = loadImage("src/main/resources/player/player_left2.png");
        playerLeftGif[2] = loadImage("src/main/resources/player/player_left3.png");
        playerLeftGif[3] = loadImage("src/main/resources/player/player_left4.png");


        imageList.add(playerGif);
        imageList.add(playerUpGif);
        imageList.add(playerRightGif);
        imageList.add(playerLeftGif);



        

        


        //Enemy Red Sprite
        PImage[] redEnemySprite = new PImage[4];
        redEnemySprite [0] = loadImage("src/main/resources/red_enemy/red_down1.png");
        redEnemySprite [1] = loadImage("src/main/resources/red_enemy/red_down2.png");
        redEnemySprite [2] = loadImage("src/main/resources/red_enemy/red_down3.png");
        redEnemySprite [3] = loadImage("src/main/resources/red_enemy/red_down4.png");

        PImage[] redEnemyUpSprite = new PImage[4];
        redEnemyUpSprite[0] = loadImage("src/main/resources/red_enemy/red_up1.png");
        redEnemyUpSprite[1] = loadImage("src/main/resources/red_enemy/red_up2.png");
        redEnemyUpSprite[2] = loadImage("src/main/resources/red_enemy/red_up3.png");
        redEnemyUpSprite[3] = loadImage("src/main/resources/red_enemy/red_up4.png");

        PImage[] redEnemyRightSprite = new PImage[4];
        redEnemyRightSprite[0] = loadImage("src/main/resources/red_enemy/red_right1.png");
        redEnemyRightSprite[1] = loadImage("src/main/resources/red_enemy/red_right2.png");
        redEnemyRightSprite[2] = loadImage("src/main/resources/red_enemy/red_right3.png");
        redEnemyRightSprite[3] = loadImage("src/main/resources/red_enemy/red_right4.png");

        PImage[] redEnemyLeftSprite = new PImage[4];
        redEnemyLeftSprite[0] = loadImage("src/main/resources/red_enemy/red_left1.png");
        redEnemyLeftSprite[1] = loadImage("src/main/resources/red_enemy/red_left2.png");
        redEnemyLeftSprite[2] = loadImage("src/main/resources/red_enemy/red_left3.png");
        redEnemyLeftSprite[3] = loadImage("src/main/resources/red_enemy/red_left4.png");


        redEnemyImageList.add(redEnemySprite);
        redEnemyImageList.add(redEnemyUpSprite);
        redEnemyImageList.add(redEnemyRightSprite);
        redEnemyImageList.add(redEnemyLeftSprite);



        //Enemy Yellow Sprite
                //Enemy Red Sprite
                PImage[] yellowEnemySprite = new PImage[4];
                yellowEnemySprite [0] = loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
                yellowEnemySprite [1] = loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
                yellowEnemySprite [2] = loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
                yellowEnemySprite [3] = loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
        
                PImage[] yellowEnemyUpSprite = new PImage[4];
                yellowEnemyUpSprite[0] = loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
                yellowEnemyUpSprite[1] = loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
                yellowEnemyUpSprite[2] = loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
                yellowEnemyUpSprite[3] = loadImage("src/main/resources/yellow_enemy/yellow_up4.png");
        
                PImage[]yellowEnemyRightSprite = new PImage[4];
                yellowEnemyRightSprite[0] = loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
                yellowEnemyRightSprite[1] = loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
                yellowEnemyRightSprite[2] = loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
                yellowEnemyRightSprite[3] = loadImage("src/main/resources/yellow_enemy/yellow_right4.png");
        
                PImage[] yellowEnemyLeftSprite = new PImage[4];
                yellowEnemyLeftSprite[0] = loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
                yellowEnemyLeftSprite[1] = loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
                yellowEnemyLeftSprite[2] = loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
                yellowEnemyLeftSprite[3] = loadImage("src/main/resources/yellow_enemy/yellow_left4.png");
        

                yellowEnemyImageList.add(yellowEnemySprite);
                yellowEnemyImageList.add(yellowEnemyUpSprite);
                yellowEnemyImageList.add(yellowEnemyRightSprite);
                yellowEnemyImageList.add(yellowEnemyLeftSprite);
        


                //Board
                Board board = new Board();
                firstBoard = board.make1stBoard();
                secondBoard = board.make2ndBoard();
                map(secondBoard);
    }
        public void map(String[][] mapBoard){
                redEnemyTF = false;
                yellowEnemyTF = false;
                for(int i = 0; i < mapBoard.length; i++){
                    for(int j = 0; j < mapBoard[0].length; j++){
                        int x = 32*j;
                        int y = 64+32*i;
                        String mapTile = mapBoard[i][j];
                        if(mapTile.equals("P")){
                            this.player = new Player(x, y-16, imageList);
                        }

                        if(mapTile.equals("Y")){
                            this.yellowEnemy = new yellowEnemy(x, y-16, yellowEnemyImageList);
                            yellowEnemyTF = true;
                        }

                        if(mapTile.equals("R")){
                            this.redEnemy = new redEnemy(x, y-16, redEnemyImageList);
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

            } else if (keyCode == UP) {
                player.changeOrientation(1);

            } else if (keyCode == RIGHT) {
                player.changeOrientation(2);

            } else if (keyCode == LEFT) {
                player.changeOrientation(3);

            }
        }
    }
    
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
