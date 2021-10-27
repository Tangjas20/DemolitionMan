package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class Bomb {
    private int x;
    private int y;
    private List<PImage[]> bombImages = new ArrayList<PImage[]>();

    public Bomb(int x, int y, List<PImage[]> bombImages) {
        this.x = x;
        this.y = y;
        this.bombImages = bombImages;
    }

    
    
}
