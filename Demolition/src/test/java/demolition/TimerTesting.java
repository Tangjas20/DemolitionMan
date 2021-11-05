package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimerTesting {

    @Test
    public void timerTick(){//Tests if timer ticks down after 60 frames.
        App app = new App();
        app.noLoop();
        app.isTest = true;
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();
        app.delay(1000);
        for(int i = 0; i < 62; i++) 
            app.timerIcon.tick();
        app.draw();
        assert(app.timerIcon.timer == 179);
    }
}