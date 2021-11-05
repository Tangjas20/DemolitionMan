package demolition;

import processing.core.PApplet;
import processing.core.PImage;


/**
* Inherits from the GameObject class and is used as a class to be inherited from for each respect tile. This is abstract as it will not be instantiated directly.
 */
public abstract class allTilesClass extends GameObject {

    protected PImage block;
    /**
    * Passes parameters of X and Y and PApplet app to the parent GameObject variable to create an object
     */
    public allTilesClass(int x, int y, PApplet app){
        super(x, y, app);
    }


    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.block, this.x*32, this.y*32+64);
    }
}

