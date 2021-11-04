package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class redEnemyTesting{

    @Test
    public void moveRedEnemyUpDown(){
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

    @Test
    public void moveRedEnemyLeftRight(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        redEnemy r = new redEnemy(3, 3, app);
        app.draw();
        r.changeOrientation(2);
        app.moveRedEnemyAI(r);
        app.draw();
        assertTrue(!app.currentBoard[3][3].equals("R"));
        r.changeOrientation(3);
        app.moveRedEnemyAI(r);
        app.draw();
        assertTrue(app.currentBoard[3][3].equals("R"));
    }

    @Test//x=13, y=2
    public void redEnemyDeadEnd(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        redEnemy r = new redEnemy(13, 2, app);
        app.draw();
        r.changeOrientation(1);
        app.moveRedEnemyAI(r);
        app.moveRedEnemyAI(r);
        assertTrue(!app.currentBoard[2][13].equals("R"));

    }

    @Test 
    public void tickRedEnemy(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        redEnemy r = new redEnemy(13, 2, app);
        for(int i = 0; i < 60; i++)
            r.tick();
        assert(r.imageTick != 0);
    }
    
}