package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

public class SolidWall extends allTilesClass {

    public SolidWall(int x, int y, PApplet app) {
        super(x, y, app);
        this.block = app.loadImage("src/main/resources/wall/solid.png");
    }

}
