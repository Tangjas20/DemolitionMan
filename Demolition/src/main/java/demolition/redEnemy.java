package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;


public class redEnemy {
    
    private int x;
    private int y;
    private int imageTick = 0;
    private List<PImage[]> enemyRedSprite = new ArrayList<PImage[]>();
    int orientation = 0;

    public redEnemy(int x, int y, List<PImage[]> enemyRedSprite) {
        this.x = x;
        this.y = y;
        this.enemyRedSprite = enemyRedSprite;
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

        app.image(this.enemyRedSprite.get(orientation)[imageTick], this.x, this.y);
    }

    

}
