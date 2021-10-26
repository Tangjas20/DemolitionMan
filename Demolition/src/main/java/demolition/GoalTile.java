package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class GoalTile {
    private int x;
    private int y;
    private PImage goal;

    public GoalTile(int x, int y, PImage goal) {
        this.x = x;
        this.y = y;
        this.goal = goal;
    }

    public void tick() {
        //Handles logic
        goal.resize(32, 32);
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.goal, this.x*32, this.y*32+64);
    }
}
