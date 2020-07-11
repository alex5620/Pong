package GameEngine.GameObjects;

import GameEngine.Graphics.Renderer;
import GameEngine.States.GameState;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public static int instances=0;
    private float velY=6;
    private int id;
    public Player(GameState gameState){
        id=instances;
        ++instances;
        this.gameState = gameState;
        this.objectImage= gameState.getImagesLoader().getImage("player");
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        setToInitialPosition();
    }
    @Override
    public void update() {
        if(id==1) {
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_UP)) {//update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_DOWN)) { //update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
        }
        else
        {
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_S)) {//update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_W)) { //update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int)posY);
    }
    public void setToInitialPosition() {
        if (id == 0) {
            posX = 115;
            posY = 335;
        } else {
            posX = 885;
            posY = 335;
        }
    }
    protected void checkForBoundaries()
    {
        if(posY<165)
        {
            posY=165;
        }
        if(posY+height>578)
        {
            posY=578-height;
        }
    }
    public float getY()
    {
        return posY;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
    public int getId()
    {
        return id;
    }
    public static void resetInstanes()
    {
        instances=0;
    }
}