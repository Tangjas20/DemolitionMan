package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BombTesting {

    @Test
    public void testMultipleBombLocation(){//Create multiple bomb and tests its location and state
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Bomb e = new Bomb(5, 5, app, app.currentBoard);
        assertNotNull(e);
        assertEquals(5, e.getX());
        assertEquals(5, e.getY());
        assertTrue(!e.explosionFinished);
        Bomb d = new Bomb(6, 6, app, app.currentBoard);
        assertNotNull(d);
        assertEquals(6, d.getX());
        assertEquals(6, d.getY());
        assertTrue(!d.explosionFinished);
    }

    @Test
    public void testBombExplode(){//Tests whether the bomb exploded or not
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Bomb e = new Bomb(5, 5, app, app.currentBoard);
        for(int i = 0; i < 31; i++){
            e.drawExplosion(app);
        }
        assertTrue(e.explosionFinished);
    }

    @Test
    public void bombBreakBlock(){//Bomb explosion breaks block
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Bomb e = new Bomb(5, 1, app, app.currentBoard);
        e.explosion(app);
        assertTrue(!e.getExplosionFinished());
        assertEquals(app.currentBoard[1][6], " ");
        assertNotNull(e.getBoard());
    }

    @Test
    public void bombBreakMultipleBlock(){//Bomb explosion breaks blocks in all directions
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Bomb e = new Bomb(9, 1, app, app.currentBoard);
        e.explosion(app);
        assertEquals(app.currentBoard[1][8], " ");
        assertEquals(app.currentBoard[1][10], " ");
        int brokenSize = app.board.getBrokenWallsList().size();
    
        Bomb d = new Bomb(9, 5, app, app.currentBoard);
        d.explosion(app);
        // x = 9, y = 5
        app.board.explosionBreakBlock(app.currentBoard, app);
        int brokenSizeAfterExplosion = app.board.getBrokenWallsList().size();
        assertEquals(app.currentBoard[5][11], " ");
        assertEquals(app.currentBoard[7][9], " ");
        assertTrue(brokenSizeAfterExplosion < brokenSize);
        assertNotNull(app.board.getEmptyWallsList().size());
    }

    @Test
    public void bombKillPlayerKillEnemy(){//Tests if bomb kills player and enemies
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        Bomb e = new Bomb(3, 1, app, app.currentBoard);
        yellowEnemy y = new yellowEnemy(4, 1, app);
        app.currentBoard[1][4] = "Y";
        assertTrue(e.getIsAlive());
        e.explosion(app);
        assertTrue(e.killedPlayer);
        assertTrue(e.killedRedEnemy);
        assertTrue(e.killedYellowEnemy);
    }



    
}