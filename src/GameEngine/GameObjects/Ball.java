package GameEngine.GameObjects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameEngine.Graphics.Renderer;
import GameEngine.States.GameState;

public class Ball extends GameObject {
    private ArrayList<Float> xCoordinates;
    private double velX=11;
    private double velY=0;
    private int id;
    private boolean isMoving=false;
    public Ball(GameState gameState) {
        this.gameState = gameState;
        this.objectImage = gameState.getImagesLoader().getImage("ball");
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        xCoordinates=new ArrayList();
        id=(int)(Math.random()*10)%2;
        setToPosition();
    }
    @Override
    public void update() {
        if(isMoving) {
            //checkIfBallIsBlocked();
            posX += velX;
            posY += velY ;
            playerCollision();
            if (posY < 165) {
                posY=166;
                reverseVelY();
                gameState.getSoundsLoader().getSound("wall").play();
            }
            if (posY + height > 578) {
                posY=577-height;
                reverseVelY();
                gameState.getSoundsLoader().getSound("wall").play();
            }
            if (posX < 105) {
                id=0;
                gameState.increaseScore2();
                isMoving=false;
                resetVelY();
            }
            if (posX > 905 - width) {
                if(gameState.isSinglePlayer()==true)
                {
                    gameState.getSmartPlayer().getTime();
                }
                id=1;
                gameState.increaseScore1();
                isMoving=false;
                resetVelY();
            }
        }
        else
        {
            setToPosition();
            if (id ==0 && gameState.getKeyboardInput().isKey(KeyEvent.VK_SPACE)) {//update pozitie jucator
                isMoving=true;
            }
            if (id ==1 && gameState.getKeyboardInput().isKey(KeyEvent.VK_ENTER)) {//update pozitie jucator
                isMoving=true;
            }
        }
    }
    public void playerCollision()
    {
        GameObject player1= gameState.getPlayer(0);
        GameObject player2= gameState.getPlayer(1);
        if(posX < player1.getPosX()+player1.getWidth() && posY > player1.getPosY() && posY < player1.getPosY()+player1.getHeight()-height)
        {
            posX=player1.getPosX()+player1.getWidth()-1;
        }
        else
        {
            if(posX+width > player2.getPosX() && posY > player2.getPosY() && posY < player2.getPosY()+player2.getHeight()-height) {
                posX = player2.getPosX()+1-width;
            }
        }
    }
    public void checkIfBallIsBlocked()
    {
        if(xCoordinates.size()<6) {
            xCoordinates.add(posX);
            return;
        }
        xCoordinates.remove(0);
        xCoordinates.add(posX);
        if(xCoordinates.stream().distinct().toArray().length==2)
        {
            if(posX<400)
            {
                posX+=3/2*width;
            }
            else
            {
                posX-=3/2*width;
            }
        }
    }
    public void changeVelY(int val)
    {
        if (val < 0) {
            decreaseVelY(val);
        } else {
            increaseVelY(val);
        }
    }
    public void decreaseVelY(int val)
    {
        while(velY>=-8 && val!=0) {
            --velY;
            ++val;
        }
    }
    public void increaseVelY(int val)
    {
        while(velY<=8 && val!=0) {
            ++velY;
            --val;
        }
    }
    @Override
    public void render(Renderer r) {
        if (gameState.checkIfWon() == false) {
            r.drawImage(objectImage, (int) posX, (int) posY);
        }
    }
    public void setToPosition()
    {
        Player player=(Player) gameState.getPlayer(id);
        if(id==0)
            posX=player.getPosX()+player.getWidth();
        else
            posX=player.getPosX()-player.getWidth();
        posY=player.getY()+player.getHeight()/2-height/2;
        velX=11;
        velY=0;
    }
    public void freeTheBall()
    {
        if(velX>0)
        {
            velX*=-1;
        }
        posX-=width;
    }
    public void resetVelY()
    {
        velY=0;
    }
    public void reverseVelX()
    {
        velX*=-1;
    }
    public void reverseVelY()
    {
        velY*=-1;
    }
    public double getVelX()
    {
        return velX;
    }
    public double getVelY()
    {
        return velY;
    }
    public void setMoving(boolean value)
    {
        isMoving=value;
    }
    public boolean isMoving()
    {
        return isMoving;
    }
    public int getId()
    {
        return id;
    }
    public void setSpeedX(double value)
    {
        velX=value;
    }
    public void setSpeedY(double value)
    {
        velY=value;
    }
}