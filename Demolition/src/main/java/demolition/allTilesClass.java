package demolition;

import processing.core.PApplet;
import processing.core.PImage;



public abstract class allTilesClass extends GameObject {

    protected PImage block;
    public allTilesClass(int x, int y, PApplet app){
        super(x, y, app);
    }


    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.block, this.x*32, this.y*32+64);
    }
}

