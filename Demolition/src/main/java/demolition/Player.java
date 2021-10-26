package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;


public class Player {
    
    private int x;
    private int y;
    private int newX;
    private int newY;
    private int imageTick = 0;
    private List<PImage[]> sprites = new ArrayList<PImage[]>();
    int orientation = 0;

    public Player(int x, int y, List<PImage[]> sprites) {
        this.x = x;
        this.y = y;
        this.sprites = sprites;
    }

    public void tick() {
        //Handles logic
            if(imageTick < 3){
                imageTick++;
            }
            else{
                imageTick = 0;
            }
        
    }
    public void movementLeft(){
        newX = x-1;
        newY = y;
        x = newX;
        y = newY;
    }

    public void movementRight(){
        newX = x+1;
        x = newX;
    }
    public void movementUP(){
        newX = x;
        newY = y-1;
        x = newX;
        y = newY;
    }

    public void movementDOWN(){
        newX = x;
        newY = y+1;
        x = newX;
        y = newY;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }



    public void changeOrientation(int orientation){
        this.orientation = orientation;
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.sprites.get(orientation)[imageTick], this.x*32, this.y*32+48);
    }

    

}
