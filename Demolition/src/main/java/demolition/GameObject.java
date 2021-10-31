package demolition;
import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public abstract class GameObject {

    protected int x;
    protected int y;

    public GameObject(int x, int y, PApplet app){
        this.x =x;
        this.y = y;
        
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }



}