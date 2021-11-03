package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class playerTesting {

    @Test
    public void createPlayerObject(){
        App app = new App();
        app.isTest = true;
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Player p = new Player(4, 4, app);
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 4);
        assertTrue(p instanceof Player);

    }

    @Test
    public void movePlayerObject(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Player p = new Player(6, 6, app);
        p.movementRight();
        assertEquals(p.getX(), 7);
        p.movementLeft();
        assertEquals(p.getX(), 6);
        p.movementUP();
        assertEquals(p.getY(), 5);
        p.movementDOWN();
        assertEquals(p.getY(), 6);
    }

    @Test
    public void playerLives(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        assertEquals(app.getLives(), 1);
    }

   /* @Test
    public void playerDead(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        Player p = new Player(1, 1, app);
        redEnemy r = new redEnemy(2, 1, app);
        p.movementRight();
        app.delay(1000);

        p.movementRight();
        assertEquals(app.getLives(), 0);
    }
    */
}