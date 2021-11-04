package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class yellowEnemyTesting{

    @Test
    public void moveYellowEnemy(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        app.delay(1000);
        yellowEnemy y = new yellowEnemy(3, 2, app);
        app.currentBoard[2][3] = "Y";
        app.draw();
        app.moveYellowEnemy(r);
        app.draw();
        assertTrue(!app.currentBoard[2][3].equals("Y"));

    }
    
}