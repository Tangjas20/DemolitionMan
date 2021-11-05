package demolition;


import processing.core.PImage;
import processing.core.PApplet;


/**
* Creates the timer class which loads the images as well as keeps track of the game time. App ticks at a rate of 60 FPS and the timer is reduced every 60 ticks
*/
public class timer {
    private int counter = 0;
    private PImage timerImage;
    int timer;
    private PImage playerIcon;

    /**
    * Takes in an int for timer which is loaded from the config.kson and the PApplet app. Timer icons and player graphics are loaded
    */
    public timer(int timer, PApplet app) {
        this.timer = timer;
        this.timerImage = app.loadImage("src/main/resources/icons/clock.png");
        this.playerIcon = app.loadImage("src/main/resources/icons/player.png");
    }
    /**
    * Every 60 ticks leads to the time -1 as there is 60FPS. 
    */
    public void tick() {
        //Handles logic
        counter++;
        if(counter == 60){
            timer -= 1;
            counter = 0;
        }

    }
    /**
    * Draws the timerIcon as well as the playerIcon which is at the top of the game window.
    */
    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics
        app.image(this.timerImage, 300, 16);
        app.image(this.playerIcon, 96, 16);
    }

    public int getTimer() {
        return this.timer;
    }
}
