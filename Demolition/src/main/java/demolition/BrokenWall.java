package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class BrokenWall {
    private int x;
    private int y;
    private PImage broken;

    public BrokenWall(int x, int y, PImage broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }

    public void tick() {
        //Handles logic
        broken.resize(32, 32);
        
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics
        app.image(this.broken, this.x*32, this.y*32+64);
    }
}
