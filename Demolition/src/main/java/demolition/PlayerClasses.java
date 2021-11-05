package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

/**
* This abstract class will be inherited from for each entity that is either a player or an enemy. Contains attributes which stores newX, newY, imageTick, sprites, orientation, timer and isEnemyDead values.
 */
public abstract class PlayerClasses extends GameObject{
    protected int newX;
    protected int newY;
    protected int imageTick = 0;
    protected List<PImage[]> sprites = new ArrayList<PImage[]>();
    protected int orientation = 0;
    int timer;
    protected boolean isEnemyDead;

    public PlayerClasses(int x, int y, PApplet app){
        super(x, y, app);
    }

    /**
    * Predominantly used for cycling through player and enemy animations. Every tick increases the timer value wherein at the count of 12, will increase the imageTick and reset the timer back to 0. 
    */
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
    }

    /**
    * Shifts the character location to the left 
    */
    public void movementLeft(){
            newX = x-1;
            newY = y;
            x = newX;
            y = newY;
        }
    /**
    * Shifts character location to the right
    */
    public void movementRight(){
        newX = x+1;
        x = newX;
    }
    /**
    * Shifts character location up 
    */
    public void movementUP(){
        newX = x;
        newY = y-1;
        x = newX;
        y = newY;
    }
    /**
    * Shifts character location Down
    */
    public void movementDOWN(){
        newX = x;
        newY = y+1;
        x = newX;
        y = newY;
    }
    /**
    * Forcelly change the character orientation (used for testing purposes) 
    * @param orientation is the orientation that the player/enemy will be changed to. It is the direction they are facing
    */
    public void changeOrientation(int orientation){
        this.orientation = orientation;
    }

}