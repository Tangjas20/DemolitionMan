package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class BrokenWall extends allTilesClass {

    public BrokenWall(int x, int y, PApplet app) {
        super(x, y, app);
        this.block = app.loadImage("src/main/resources/broken/broken.png");
    }



}
