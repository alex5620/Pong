package GameEngine;

import java.awt.event.KeyEvent;

public class Ball extends GameObject {
    private int velX=10;
    private int velY=10;
    private int id;
    private boolean isMoving=false;
    public Ball(GameState gameState) {
        this.gameState = gameState;
        this.objectImage = gameState.getImagesLoader().getImage("ball");
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        id=0;
    }
    @Override
    public void update() {
        if(isMoving) {
            posX += velX;
            posY += velY ;
            if (posY < 165) {
                reverseVelY();
            }
            if (posY + height > 578) {
                reverseVelY();
            }
            if (posX < 105) {
                id=0;
                gameState.increseScore2();
                isMoving=false;
            }
            if (posX > 906 - width) {
                id=1;
                gameState.increaseScore1();
                isMoving=false;
            }
        }
        else
        {
            Player player=(Player) gameState.getPlayer(id);
            setToPosition();
            if (id ==0 && gameState.getKeyboardInput().isKey(KeyEvent.VK_SPACE)) {//update pozitie jucator
                isMoving=true;
            }
            if (id ==1 && gameState.getKeyboardInput().isKey(KeyEvent.VK_ENTER)) {//update pozitie jucator
                isMoving=true;
            }
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int) posY);
    }
    public void setToPosition()//mingea este pozitionata deasupra player-ului
    {
        Player player=(Player) gameState.getPlayer(id);
        if(id==0)
            posX=player.getPosX()+player.getWidth();
        else
            posX=player.getPosX()-player.getWidth();
        posY=player.getY()+player.getHeight()/2-height/2;
    }
    public void reverseVelX()
    {
        velX*=-1;
    }
    public void reverseVelY()
    {
        velY*=-1;
    }
    public void setMoving(boolean value)
    {
        isMoving=value;
    }
}