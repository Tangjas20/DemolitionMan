package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class SolidWall {
    private int x;
    private int y;
    private PImage solid;

    public SolidWall(int x, int y, PApplet app) {
        this.x = x;
        this.y = y;
        this.solid = app.loadImage("src/main/resources/wall/solid.png");
    }

    public void tick() {
        //Handles logic
        solid.resize(32, 32);
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.solid, this.x*32, this.y*32+64);
    }
}
