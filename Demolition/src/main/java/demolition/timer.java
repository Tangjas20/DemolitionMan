package demolition;


import processing.core.PImage;
import processing.core.PApplet;

public class timer {

    private PImage timerImage;
    private int timer;

    public timer(int timer, PImage timerImage) {
        this.timer = timer;
        this.timerImage = timerImage;

    }

    public void tick() {
        //Handles logic
        timer -= 1;
        
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics
        app.image(this.timerImage, 96, 16);
    }
}
