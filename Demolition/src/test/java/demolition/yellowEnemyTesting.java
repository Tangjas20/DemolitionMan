package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class yellowEnemyTesting{

    @Test
    public void moveYellowEnemyUpDown(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        yellowEnemy y = new yellowEnemy(3, 2, app);
        app.currentBoard[2][3] = "Y";
        app.draw();
        app.moveYellowEnemy(y);
        app.draw();
        assertTrue(!app.currentBoard[2][3].equals("Y"));
        y.changeOrientation(1);
        app.moveYellowEnemy(y);
        app.draw();
        assertTrue(!app.currentBoard[3][3].equals("Y"));
    }
    
    @Test
    public void moveYellowEnemyRightLeft(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        yellowEnemy y = new yellowEnemy(3, 3, app);
        y.changeOrientation(2);
        app.moveYellowEnemy(y);
        app.draw();
        assertTrue(app.currentBoard[3][4].equals("Y"));
        y.changeOrientation(3);
        app.moveYellowEnemy(y);
        app.draw();
        assertTrue(app.currentBoard[3][3].equals("Y"));
    }
}