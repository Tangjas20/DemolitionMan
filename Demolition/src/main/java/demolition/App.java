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
    private List<yellowEnemy> yellowList = new ArrayList<yellowEnemy>();


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

        List<PImage[]> imageList = new ArrayList<PImage[]>();
        imageList.add(playerGif);
        imageList.add(playerUpGif);
        imageList.add(playerRightGif);
        imageList.add(playerLeftGif);

        this.player = new Player(30, 200, imageList);

        //Tiles

        //GoalTile
        this.GoalImage = new GoalTile(100, 200, this.loadImage("src/main/resources/goal/goal.png"));

        //SolidTile
        this.SolidImage = new SolidWall(160, 200, this.loadImage("src/main/resources/wall/solid.png"));

        //EmptyTile
        this.EmptyImage = new EmptyWall(220, 200, this.loadImage("src/main/resources/empty/empty.png"));

        //Broken
        this.BrokenImage = new BrokenWall(280, 200, this.loadImage("src/main/resources/broken/broken.png"));


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

        List<PImage[]> redEnemyImageList = new ArrayList<PImage[]>();
        redEnemyImageList.add(redEnemySprite);
        redEnemyImageList.add(redEnemyUpSprite);
        redEnemyImageList.add(redEnemyRightSprite);
        redEnemyImageList.add(redEnemyLeftSprite);

        this.redEnemy = new redEnemy(320, 200, redEnemyImageList);

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
        
                List<PImage[]> yellowEnemyImageList = new ArrayList<PImage[]>();
                yellowEnemyImageList.add(yellowEnemySprite);
                yellowEnemyImageList.add(yellowEnemyUpSprite);
                yellowEnemyImageList.add(yellowEnemyRightSprite);
                yellowEnemyImageList.add(yellowEnemyLeftSprite);
        
                this.yellowEnemy = new yellowEnemy(380, 200, yellowEnemyImageList);
                yellowList.add(yellowEnemy);
                this.yellowEnemy = new yellowEnemy(450, 200, yellowEnemyImageList);
                yellowList.add(yellowEnemy);

                //Board
                Board board = new Board();
                firstBoard = board.make1stBoard();
                secondBoard = board.make2ndBoard();
                /*for(int i = 0; i < firstBoard.length; i++){
                    for(int j = 0; j < firstBoard[0].length; j++){
                        String mapTile = firstBoard[i][j];
                        if(mapTile == "W"){
                            
                        }
                    }
                }*/
    }

    public void draw() {
        //Main loop here        
        if(frameCount % 12 == 1){
            background(255, 128, 0);
            this.redEnemy.tick();
            this.redEnemy.draw(this);

            for(yellowEnemy i: yellowList){
            this.yellowEnemy = i;
            this.yellowEnemy.tick();
            this.yellowEnemy.draw(this);
            }

            this.GoalImage.draw(this);

            this.SolidImage.draw(this);

            this.EmptyImage.draw(this);

            this.BrokenImage.draw(this);

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
