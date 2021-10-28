package demolition;


import processing.core.PImage;
import processing.core.PApplet;



public class timer {

    private PImage timerImage;
    private int timer;
    private PImage playerIcon;


    public timer(int timer, PApplet app) {
        this.timer = timer;
        this.timerImage = app.loadImage("src/main/resources/icons/clock.png");
        this.playerIcon = app.loadImage("src/main/resources/icons/player.png");
    }

    public void tick() {
        //Handles logic
        timer -= 1;
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics
        app.image(this.timerImage, 300, 16);
        app.image(this.playerIcon, 96, 16);
    }

    public int getTimer() {
        return this.timer;
    }
}
