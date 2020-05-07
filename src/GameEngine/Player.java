package GameEngine;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public static int instances=0;
    private float velY=5;
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
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_RIGHT)) {//update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_LEFT)) { //update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
        }
        else
        {
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_D)) {//update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
            if (gameState.getKeyboardInput().isKey(KeyEvent.VK_A)) { //update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int)posY);
    }                                                 //dimensiunea player-ului
    protected void checkForBoundaries()            //se verifica limita din stanga si din dreapta a ferestrei
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
    public float getX()
    {
        return posX;
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
    public void setToInitialPosition() {
        if (id == 0) {
            posX = 115;
            posY = 335;
        } else {
            posX = 885;
            posY = 335;
        }
    }
}