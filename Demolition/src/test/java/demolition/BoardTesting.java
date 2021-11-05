package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTesting {

    @Test//Tests to see if the board loads
    public void boardLoad(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        assertNotNull(app.getCurrentBoard());
        assert(app.currentBoard[1][1].equals("P"));
    }


    @Test
    public void timerAtZero() {//Since timer is used in the IF function, only gameOver can be asserted for true/false
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.changeLives(2);
        app.currentTimer = 0;
        for(int i = 0; i < 62; i++)
            app.draw();
        assert(app.gameOver == false); //Game over is only true when the player reaches the goal tile

    }

    @Test
    public void testFrameCountAndEntities(){//Tests to see if entities are loaded in
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.frameCount = 60;
        app.draw();
        app.draw();
        assert(app.board.yellowEnemyTF == true);
        assert(app.board.redEnemyTF == true);
        assertEquals(app.currentBoard[2][1], " ");
    }

    @Test
    public void gameOverTesting(){//
        App app = new App();
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.gameOver = true;
        app.draw();
        assert(app.gameOver == true);
    }

    @Test
    public void loadNonTestConfig(){//Testing to see if actual config file can be loaded, and not just testconfig
        App app = new App();
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        assertNotNull(app.currentBoard);
        assertNotNull(app.board.getPlayer());
    }


}