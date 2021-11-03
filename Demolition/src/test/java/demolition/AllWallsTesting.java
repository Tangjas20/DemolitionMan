package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllWallsTesting{
    
    @Test
    public void simpleTest() {
        assertEquals(480, App.HEIGHT);
    }

    @Test
    public void createEmptyWall(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        EmptyWall e = new EmptyWall(1, 1, app);
        assertNotNull(e);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 1);
    }

    @Test
    public void createSolidWall(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        SolidWall e = new SolidWall(1, 1, app);
        assertNotNull(e);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 1);
    }

    @Test
    public void createBrokenWall(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        BrokenWall e = new BrokenWall(3, 1, app);
        assertNotNull(e);
        assertEquals(e.getX(), 3);
        assertEquals(e.getY(), 1);
    }

    @Test
    public void createGoalTile(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        GoalTile e = new GoalTile(1, 5, app);
        assertNotNull(e);
        assertEquals(e.getX(), 1);
        assertEquals(e.getY(), 5);
    }




}
