package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class EmptyWall {
    private int x;
    private int y;
    private PImage empty;

    public EmptyWall(int x, int y, PApplet app) {
        this.x = x;
        this.y = y;
        this.empty = app.loadImage("src/main/resources/empty/empty.png");
    }

    public void tick() {
        //Handles logic
        empty.resize(32, 32);
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics
        app.image(this.empty, this.x*32, this.y*32+64);
    }
}
