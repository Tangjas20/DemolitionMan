package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;


public class yellowEnemy {
    
    private int x;
    private int y;
    private int imageTick = 0;
    private List<PImage[]> enemyYellowSprite = new ArrayList<PImage[]>();
    int orientation = 0;

    public yellowEnemy(int x, int y, PApplet app) {
        this.x = x;
        this.y = y;
        PImage[] yellowEnemySprite = new PImage[4];
        yellowEnemySprite [0] = app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        yellowEnemySprite [1] = app.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
        yellowEnemySprite [2] = app.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
        yellowEnemySprite [3] = app.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");

        PImage[] yellowEnemyUpSprite = new PImage[4];
        yellowEnemyUpSprite[0] = app.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
        yellowEnemyUpSprite[1] = app.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
        yellowEnemyUpSprite[2] = app.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
        yellowEnemyUpSprite[3] = app.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");

        PImage[]yellowEnemyRightSprite = new PImage[4];
        yellowEnemyRightSprite[0] = app.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
        yellowEnemyRightSprite[1] = app.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
        yellowEnemyRightSprite[2] = app.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
        yellowEnemyRightSprite[3] = app.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");

        PImage[] yellowEnemyLeftSprite = new PImage[4];
        yellowEnemyLeftSprite[0] = app.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
        yellowEnemyLeftSprite[1] = app.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
        yellowEnemyLeftSprite[2] = app.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
        yellowEnemyLeftSprite[3] = app.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");


        enemyYellowSprite.add(yellowEnemySprite);
        enemyYellowSprite.add(yellowEnemyUpSprite);
        enemyYellowSprite.add(yellowEnemyRightSprite);
        enemyYellowSprite.add(yellowEnemyLeftSprite);
    }

    public void tick() {
        //Handles logic
            if(imageTick < 3){
                imageTick++;
            }
            else{
                imageTick = 0;
            }
    }


    public void changeOrientation(int orientation){
        this.orientation = orientation;
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.enemyYellowSprite.get(orientation)[imageTick], this.x*32, this.y*32+48);
    }

    

}
