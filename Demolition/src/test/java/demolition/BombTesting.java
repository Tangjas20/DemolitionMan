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
        
        Bomb f = new Bomb(3, 1, app, app.currentBoard);
        f.explosion(app);
        f.drawExplosion(app);
        for(int i = 0; i < 160 ; i++)
            f.draw(app);
        assert(f.timerSprite != 0);
        assert(f.getCurrentSprite() != 0);
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
        e.drawExplosion(app);
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

        Bomb f = new Bomb(9, 1, app, app.currentBoard);
        f.setCanExplodePath();
        f.explosion(app);
        f.drawExplosion(app);
        app.draw();
        assertEquals(app.currentBoard[1][8], " ");
        assertEquals(app.currentBoard[1][10], " ");
        assertEquals(app.currentBoard[1][7], " ");
        assertEquals(app.currentBoard[1][11], " ");

        Bomb g = new Bomb(2, 5, app, app.currentBoard);
        g.setCanExplodePath();
        g.explosion(app);
        g.drawExplosion(app);
        app.draw();
        app.board.explosionBreakBlock(app.currentBoard, app);
        app.draw();
        assertEquals(app.currentBoard[7][2], " ");

        Bomb h = new Bomb(7, 8, app, app.currentBoard);
        h.setCanExplodePath();
        h.explosion(app);
        h.drawExplosion(app);
        app.draw();
        assertEquals(app.currentBoard[9][7], " ");

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
        app.bombList.add(e);
        for(Bomb i: app.bombList){
            i.notAlive();
        }
        app.draw();
        e.explosion(app);
        app.currentBoard[1][4] = "Y";
        e.explode(4, 1, app);
        assertEquals(app.currentBoard[1][4], " ");
        assertTrue(!e.getIsAlive());
        assertTrue(e.killedPlayer);
        assertTrue(e.killedRedEnemy);
    }

    @Test
    public void playerSpaceDropBomb(){//Player Drops a bomb and is added to the bomblist
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        app.keyCode = 32;
        app.keyPressed();
        app.keyReleased();
        app.keyPressed();
        app.keyReleased();
        for(int i = 0; i < 61 ; i++)
            app.draw();
        assertNotNull(app.getBombList());
    }
    
    @Test
    public void appBombList(){//Tests bomb added to list and state of bomb and test if the bomb explosion counter works or not
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        Bomb a = new Bomb(9, 1, app, app.currentBoard);
        Bomb b = new Bomb(3, 3, app, app.currentBoard);
        app.bombList.add(a);
        app.bombList.add(b);
        for(Bomb i: app.bombList){
            i.notAlive();
        }
        app.draw();
        assert(app.currentBoard == b.getBoard());
        assert(app.counter != 0);
    }
}