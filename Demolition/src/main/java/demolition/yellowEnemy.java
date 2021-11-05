package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

/**
* Creates the yellowEnemy object which inherits attributes from the playerClasses class for tick(). Contains a moveTimer
*/
public class yellowEnemy extends PlayerClasses{

    private List<PImage[]> enemyYellowSprite = new ArrayList<PImage[]>();
    /**
    *Creates the yellow enemy object at the specified coordinates. It will load the sprite pngs and store it in enemyYellowSprite List<PImage>
    * @param x corresponds to the x coordinate of the tile where the object is placed
    * @param y corresponds to the y coordinate of the tile where the object is placed
    */
    public yellowEnemy(int x, int y, PApplet app) {
        super(x, y, app);
        
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
//The Yellow Enemy moves in a straight line, but on collision with a wall will attempt to move clockwise 
//(if it was moving left, it will try to move up, and if unable to, then try right and then left; 
//similarly if it was moving down, it will try to move left, otherwise up or right).



    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.enemyYellowSprite.get(orientation)[imageTick], this.x*32, this.y*32+48);
    }

    

}
