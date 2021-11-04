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
    public void createEmptyWall(){//Creates new Empty Wall object and tests its location
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
    public void createSolidWall(){//Creates new Solid Wall object and tests its location
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
    public void createBrokenWall(){//Creates new Broken Wall object and tests its location
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
    public void createGoalTile(){//Creates Goal Tile object and tests its location
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
