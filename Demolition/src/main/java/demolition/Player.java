package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;


/**
* Contains attributes which stores the images for each animation in each direction.
 */
public class Player extends PlayerClasses{
    
    protected List<PImage[]> sprites = new ArrayList<PImage[]>();

    /**
    * Takes in the x and y coordinates of the object and loads in the respective images for each direction.
     */
    public Player(int x, int y, PApplet app) {
        super(x, y, app);

        PImage[] playerGif = new PImage[4];
        playerGif[0] = app.loadImage("src/main/resources/player/player1.png");
        playerGif[1] = app.loadImage("src/main/resources/player/player2.png");
        playerGif[2] = app.loadImage("src/main/resources/player/player3.png");
        playerGif[3] = app.loadImage("src/main/resources/player/player4.png");

        PImage[] playerUpGif = new PImage[4];
        playerUpGif[0] = app.loadImage("src/main/resources/player/player_up1.png");
        playerUpGif[1] = app.loadImage("src/main/resources/player/player_up2.png");
        playerUpGif[2] = app.loadImage("src/main/resources/player/player_up3.png");
        playerUpGif[3] = app.loadImage("src/main/resources/player/player_up4.png");

        PImage[] playerRightGif = new PImage[4];
        playerRightGif[0] = app.loadImage("src/main/resources/player/player_right1.png");
        playerRightGif[1] = app.loadImage("src/main/resources/player/player_right2.png");
        playerRightGif[2] = app.loadImage("src/main/resources/player/player_right3.png");
        playerRightGif[3] = app.loadImage("src/main/resources/player/player_right4.png");

        PImage[] playerLeftGif = new PImage[4];
        playerLeftGif[0] = app.loadImage("src/main/resources/player/player_left1.png");
        playerLeftGif[1] = app.loadImage("src/main/resources/player/player_left2.png");
        playerLeftGif[2] = app.loadImage("src/main/resources/player/player_left3.png");
        playerLeftGif[3] = app.loadImage("src/main/resources/player/player_left4.png");


        sprites.add(playerGif);
        sprites.add(playerUpGif);
        sprites.add(playerRightGif);
        sprites.add(playerLeftGif);
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.sprites.get(orientation)[imageTick], this.x*32, this.y*32+48);
    }

    

}
