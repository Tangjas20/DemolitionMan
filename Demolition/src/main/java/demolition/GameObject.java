package demolition;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

/**
* The GameObject class is the main abstract class which will be inherited from by the PlayerClasses and the allTilesClass for the X and Y coordinates as well as universal getter methods for X and Y 
*/
public abstract class GameObject {

    protected int x;
    protected int y;

    /**
    * Takes in the x and y coordinates and stores it in protected variables in the class attributes
     */
    public GameObject(int x, int y, PApplet app){
        this.x =x;
        this.y = y;
        
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }



}