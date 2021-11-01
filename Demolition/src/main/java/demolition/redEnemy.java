package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;
import java.util.Random;

public class redEnemy extends PlayerClasses implements playerMovement{
    
    int movementTimer = 0;
    private List<PImage[]> enemyRedSprite = new ArrayList<PImage[]>();

    public redEnemy(int x, int y, PApplet app) {
        super(x, y, app);
                //Enemy Red Sprite
        PImage[] redEnemySprite = new PImage[4];
        redEnemySprite [0] = app.loadImage("src/main/resources/red_enemy/red_down1.png");
        redEnemySprite [1] = app.loadImage("src/main/resources/red_enemy/red_down2.png");
        redEnemySprite [2] = app.loadImage("src/main/resources/red_enemy/red_down3.png");
        redEnemySprite [3] = app.loadImage("src/main/resources/red_enemy/red_down4.png");

        PImage[] redEnemyUpSprite = new PImage[4];
        redEnemyUpSprite[0] = app.loadImage("src/main/resources/red_enemy/red_up1.png");
        redEnemyUpSprite[1] = app.loadImage("src/main/resources/red_enemy/red_up2.png");
        redEnemyUpSprite[2] = app.loadImage("src/main/resources/red_enemy/red_up3.png");
        redEnemyUpSprite[3] = app.loadImage("src/main/resources/red_enemy/red_up4.png");

        PImage[] redEnemyRightSprite = new PImage[4];
        redEnemyRightSprite[0] = app.loadImage("src/main/resources/red_enemy/red_right1.png");
        redEnemyRightSprite[1] = app.loadImage("src/main/resources/red_enemy/red_right2.png");
        redEnemyRightSprite[2] = app.loadImage("src/main/resources/red_enemy/red_right3.png");
        redEnemyRightSprite[3] = app.loadImage("src/main/resources/red_enemy/red_right4.png");

        PImage[] redEnemyLeftSprite = new PImage[4];
        redEnemyLeftSprite[0] = app.loadImage("src/main/resources/red_enemy/red_left1.png");
        redEnemyLeftSprite[1] = app.loadImage("src/main/resources/red_enemy/red_left2.png");
        redEnemyLeftSprite[2] = app.loadImage("src/main/resources/red_enemy/red_left3.png");
        redEnemyLeftSprite[3] = app.loadImage("src/main/resources/red_enemy/red_left4.png");


        enemyRedSprite.add(redEnemySprite);
        enemyRedSprite.add(redEnemyUpSprite);
        enemyRedSprite.add(redEnemyRightSprite);
        enemyRedSprite.add(redEnemyLeftSprite);
    }
    public void tick() {
        //Handles logic
        timer++;
        if(timer == 12){
            imageTick ++;
            timer = 0;
            if(imageTick > 3){
                this.imageTick = 0;
            }
        }
        movementTimer ++;
        if(movementTimer = 60){
            ;
        }

    }


    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.enemyRedSprite.get(orientation)[imageTick], this.x*32, this.y*32+48);
    }

    

}
