package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.Arrays;

public class Bomb extends GameObject {
    private String[][] currentBoard;
    private PImage[] bombSprite = new PImage[8];
    private PImage[] explosionSprite = new PImage[7];
    private int[] explodablePath = {0, 0, 0, 0};
    boolean explosionFinished;
    private int timerSprite;
    private int currentSprite;
    private boolean isAlive;
    boolean killedPlayer = false;
    boolean killedYellowEnemy = false;
    boolean killedRedEnemy = false;


    public Bomb(int x, int y, PApplet app, String[][] currentBoard){
        super(x, y, app);
        this.currentBoard = currentBoard;

        this.bombSprite[0] = app.loadImage("src/main/resources/bomb/bomb1.png");
        this.bombSprite[1] = app.loadImage("src/main/resources/bomb/bomb2.png");
        this.bombSprite[2] = app.loadImage("src/main/resources/bomb/bomb3.png");
        this.bombSprite[3] = app.loadImage("src/main/resources/bomb/bomb4.png");
        this.bombSprite[4] = app.loadImage("src/main/resources/bomb/bomb5.png");
        this.bombSprite[5] = app.loadImage("src/main/resources/bomb/bomb6.png");
        this.bombSprite[6] = app.loadImage("src/main/resources/bomb/bomb7.png");
        this.bombSprite[7] = app.loadImage("src/main/resources/bomb/bomb8.png");

        
        this.explosionSprite[0] = app.loadImage("src/main/resources/explosion/centre.png");
        this.explosionSprite[1] = app.loadImage("src/main/resources/explosion/horizontal.png");
        this.explosionSprite[2] = app.loadImage("src/main/resources/explosion/vertical.png");
        this.explosionSprite[3] = app.loadImage("src/main/resources/explosion/end_top.png");
        this.explosionSprite[4] = app.loadImage("src/main/resources/explosion/end_right.png");
        this.explosionSprite[5] = app.loadImage("src/main/resources/explosion/end_bottom.png");
        this.explosionSprite[6] = app.loadImage("src/main/resources/explosion/end_left.png");

        this.currentSprite = 0;
        this.timerSprite = 0;
        this.isAlive = true;
        this.explosionFinished = false;
        setCanExplodePath();
    }

    public void setCanExplodePath(){
        if(this.currentBoard[y][x+1].equals("W")){
            ;
        }//Right
        else if(this.currentBoard[y][x+1].matches(" |R|Y|P")){
            explodablePath[3] ++;
            if(this.currentBoard[y][x+2].matches(" |R|Y|P|B")){
                explodablePath[3] ++;
            }
        }
        else if(this.currentBoard[y][x+1].equals("B")){
                explodablePath[3] += 3;
            }
        //Left
        if(this.currentBoard[y][x-1].equals("W")){
            ;
        }
        else if(this.currentBoard[y][x-1].matches(" |R|Y|P")){
            explodablePath[0] ++;
            if(this.currentBoard[y][x-2].matches(" |R|Y|P|B")){
                explodablePath[0] ++;
            }
        }
        else if(this.currentBoard[y][x-1].equals("B")){
                explodablePath[0] += 3;
            }
        //DOWN
        if(this.currentBoard[y+1][x].equals("W")){
            ;
        }
        else if(this.currentBoard[y+1][x].matches(" |R|Y|P")){
            explodablePath[2] ++;
            if(this.currentBoard[y+2][x].matches(" |R|Y|P|B")){
                explodablePath[2] ++;
            }
        }
        else if(this.currentBoard[y+1][x].equals("B")){
                explodablePath[2] += 3;
            }
        //UP
        if(this.currentBoard[y-1][x].equals("W")){
            ;
        }
        else if(this.currentBoard[y-1][x].matches(" |R|Y|P")){
            explodablePath[1] ++;
            if(this.currentBoard[y-2][x].matches(" |R|Y|P|B")){
                explodablePath[1] ++;
            }
        }
        else if(this.currentBoard[y-1][x].equals("B")){
                explodablePath[1] += 3;
            }
        
        this.explodablePath = explodablePath;
    }
    
    public void drawExplosion(PApplet app) {//Left
        app.image(explosionSprite[0], this.x*32, this.y*32+64);
        if (explodablePath[0] == 1 || explodablePath[0] == 3) app.image(explosionSprite[6], this.x*32-32, this.y*32+64); //Left Close
        else if (explodablePath[0] == 2) {//Left Far
            app.image(explosionSprite[1], this.x*32-32, this.y*32+64);
            app.image(explosionSprite[6], this.x*32-64, this.y*32+64);
        }
        //UP
        if (explodablePath[1] == 1 || explodablePath[1] == 3) app.image(explosionSprite[3], this.x*32, this.y*32+64-32);
        else if (explodablePath[1] == 2) {
            app.image(explosionSprite[2], this.x*32, this.y*32+64-32);
            app.image(explosionSprite[3], this.x*32, this.y*32+64-64);
        }//DOWN
        if (explodablePath[2] == 1 || explodablePath[2] == 3) app.image(explosionSprite[5], this.x*32, this.y*32+64+32);
        else if (explodablePath[2] == 2) {
            app.image(explosionSprite[2], this.x*32, this.y*32+32+64);
            app.image(explosionSprite[5], this.x*32, this.y*32+64+64);
        }//RIGHT
        if (explodablePath[3] == 1 || explodablePath[3] == 3) app.image(explosionSprite[4], this.x*32+32, this.y*32+64);
        else if (explodablePath[3] == 2) {
            app.image(explosionSprite[1], this.x*32+32, this.y*32+64);
            app.image(explosionSprite[4], this.x*32+64, this.y*32+64);
        }
        timerSprite += 1;
        if (timerSprite == 30) {
            this.explosionFinished = true;
        }
    }

    public void draw(PApplet app) {
        app.image(bombSprite[currentSprite], this.x*32, this.y*32+64);
        timerSprite += 1;

        if(timerSprite == 15) {
            timerSprite = 0;
            currentSprite += 1;
            if (currentSprite > 7) {
                currentSprite = 0;
                this.isAlive = false;
                timerSprite = 0;
            }
        }

    }

    public void explosion(App app) {
        //Left
      if (explode(this.x-1, this.y, app)) 
            explode(this.x-2, this.y, app);
      //Down
      if (explode(this.x, this.y+1, app))
            explode(this.x, this.y+2, app);
      //Right
      if (explode(this.x+1, this.y, app)) 
            explode(this.x+2, this.y, app);
      //Up
      if (explode(this.x, this.y-1, app)) 
            explode(this.x, this.y-2, app);
            
      if (explode(this.x, this.y, app));
    }

    public boolean explode(int row, int column, App app) {
        String tile = app.currentBoard[column][row];

        if (tile.equals(" ")) {
          return true;
        } else if (tile.equals("B")) {
            this.currentBoard[column][row] = " ";
            return false;

        } else if (tile.equals("P")) {
            killedPlayer = true;
            return true;

        } else if (tile.equals("Y")) {
            this.currentBoard[column][row] = " ";
            for(yellowEnemy i: app.board.yellowEnemyList){
                if(i.getX() == row && i.getY() == column){
                    i.isEnemyDead = true;
                }
            }
            app.board.yellowEnemyList.removeIf(b -> b.isEnemyDead == true);
            killedYellowEnemy = true;
            return true;

        } else if (tile.equals("R")) {
            this.currentBoard[column][row] = " ";
            for(redEnemy i: app.board.redEnemyList){
                if(i.getX() == row && i.getY() == column){
                    i.isEnemyDead = true;
                }
            }
            app.board.redEnemyList.removeIf(b -> b.isEnemyDead == true);
            killedRedEnemy = true;
            return true;
        }
        return false;

    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public boolean getExplosionFinished(){
        return this.explosionFinished;
    }

    public String[][] getBoard(){
        return this.currentBoard;
    }


}