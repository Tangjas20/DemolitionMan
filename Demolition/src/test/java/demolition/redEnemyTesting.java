package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class redEnemyTesting{

    @Test
    public void moveRedEnemy(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        redEnemy r = new redEnemy(3, 2, app);
        app.currentBoard[2][3] = "R";
        app.draw();
        app.moveRedEnemyAI(r);
        app.draw();
        assertTrue(!app.currentBoard[2][3].equals("R"));
        r.changeOrientation(1);
        app.moveRedEnemyAI(r);
        app.draw();
        assertTrue(!app.currentBoard[3][3].equals("R"));
    }

    
}