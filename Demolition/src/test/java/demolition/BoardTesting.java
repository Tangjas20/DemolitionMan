package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTesting {

    @Test
    public void boardLoad(){
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        assertNotNull(app.getCurrentBoard());
    }


    @Test
    public void timerAtZero() {
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.currentTimer = 0;
        app.changeLives(0);
        app.draw();
        assert(app.gameOver == false); //Game over is only true when the player reaches the goal tile
    }

    
}