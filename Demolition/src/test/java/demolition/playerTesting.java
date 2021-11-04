package demolition;

import processing.core.PConstants;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class playerTesting {

    @Test
    public void createPlayerObject(){//Create player object
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
    public void movePlayerObject(){//move player with methods
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
    public void playerLives(){//Checks if lives have been loaded correctly
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        assertEquals(app.getLives(), 2);
    }

    @Test
    public void resetGameTime(){//Resets the game 
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        app.draw();
        app.resetGame();
        assertEquals(app.getLives(), 2);
        assertEquals(app.getCurrentTimer(), 180);
        assertTrue(!app.gameReset);
    }
    
    @Test
    public void playerWalkIntoRedEnemy(){//tests if player loses a life or not when walking into red enemy
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.board.getPlayer().movementRight();
        app.draw();
        assertEquals(app.getLives(), 1);
    }

    @Test
    public void keyPressing(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.keyCode = 40;

        app.keyPressed();
        app.keyReleased();
        assertTrue(app.currentBoard[1][1].equals(" "));
        assertTrue(app.currentBoard[2][1].equals("P"));
        app.keyCode = 38;

        app.keyPressed();
        app.keyReleased();
        assertTrue(app.currentBoard[1][1].equals("P"));
        assertTrue(app.currentBoard[2][1].equals(" "));
        app.keyCode = 40;
        app.keyPressed();
        app.keyReleased();
        app.keyPressed();
        app.keyReleased();
        app.keyCode = 39;
        app.keyPressed();
        app.keyReleased();
        assertTrue(app.currentBoard[3][2].equals("P"));
        app.keyCode = 37;
        app.keyPressed();
        app.keyReleased();
        assertTrue(app.currentBoard[3][1].equals("P"));
        app.keyCode = 32;
        app.keyPressed();
        app.keyReleased();
        app.draw();
        assertNotNull(app.getBombList());
    }
    
    @Test
    public void playerWalkIntoGoal(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.board.getPlayer().movementDOWN();
        app.board.getPlayer().movementDOWN();
        app.board.getPlayer().movementRight();
        app.board.getPlayer().movementRight();
        app.board.getPlayer().movementRight();
        app.board.getPlayer().movementDOWN();
        app.board.getPlayer().movementDOWN();
        app.draw();
        assert(app.gameOver == true);
    }

    @Test
    public void playerTick(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        for(int i = 0; i < 13; i++)
            app.board.getPlayer().tick();
        app.draw();
        assertTrue(app.board.getPlayer().imageTick != 0);
        for(int i = 0; i < 40; i++)
            app.board.getPlayer().tick();
        assertTrue(app.board.getPlayer().imageTick == 0);
    }
}