package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public abstract class PlayerClasses extends GameObject{
    protected int newX;
    protected int newY;
    protected int imageTick = 0;
    protected List<PImage[]> sprites = new ArrayList<PImage[]>();
    protected int orientation = 0;

    public PlayerClasses(int x, int y, PApplet app){
        super(x, y, app);

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
    public void movementLeft(){
            newX = x-1;
            newY = y;
            x = newX;
            y = newY;
        }

    public void movementRight(){
        newX = x+1;
        x = newX;
    }
    public void movementUP(){
        newX = x;
        newY = y-1;
        x = newX;
        y = newY;
    }

    public void movementDOWN(){
        newX = x;
        newY = y+1;
        x = newX;
        y = newY;
    }

}