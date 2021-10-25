package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;


public class yellowEnemy {
    
    private int x;
    private int y;
    private int imageTick = 0;
    private List<PImage[]> enemyYellowSprite = new ArrayList<PImage[]>();
    int orientation = 0;

    public yellowEnemy(int x, int y, List<PImage[]> enemyYellowSprite) {
        this.x = x;
        this.y = y;
        this.enemyYellowSprite = enemyYellowSprite;
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

    public void changeOrientation(int orientation){
        this.orientation = orientation;
    }

    public void draw(PApplet app) {
        //Handling graphics -- Single line or so, no logics

        app.image(this.enemyYellowSprite.get(orientation)[imageTick], this.x, this.y);
    }

    

}
