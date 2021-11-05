package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;
/**
* Creates the SolidWall class which is used to create SolidWall objects that can be stored in an ArrayList for populating the board.
*/
public class SolidWall extends allTilesClass {
    /**
    * Constructs the object given the x and y coordinates as well as loading the image from the resources folder.
     */
    public SolidWall(int x, int y, PApplet app) {
        super(x, y, app);
        this.block = app.loadImage("src/main/resources/wall/solid.png");
    }

}
