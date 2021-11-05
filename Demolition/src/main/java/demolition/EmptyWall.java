package demolition;

import java.util.ArrayList;
import java.util.List;
import processing.core.PImage;
import processing.core.PApplet;

/**
* Creates the EmptyWall class which is used to create emptyWall objects that can be stored in an ArrayList for populating the board.
*/
public class EmptyWall extends allTilesClass {
    /**
    * Constructs the object given the x and y coordinates as well as loading the image from the resources folder.
    * @param x corresponds to the x coordinate of the tile where the object is placed
    * @param y corresponds to the y coordinate of the tile where the object is placed
     */
    public EmptyWall(int x, int y, PApplet app) {
        super(x, y, app);
        this.block = app.loadImage("src/main/resources/empty/empty.png");
    }


}