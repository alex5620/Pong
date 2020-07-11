package GameEngine.GameObjects;

import GameEngine.Graphics.Renderer;
import GameEngine.States.GameState;

public class SmartPlayer extends Player {
    private long time;
    public SmartPlayer(GameState gameState){
        super(gameState);
        this.objectImage= gameState.getImagesLoader().getImage("player");
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        super.setToInitialPosition();
        getTime();
    }
    public void update ()
    {
            updatePosition();
            freeTheBall();
            checkForBoundaries();
    }
    public void updatePosition() {
        if (gameState.getBall().isMoving()) {
            int impactDistance, impactTime, targetY;
            int speed = 25; // a little slower than the human player
            Ball ball = gameState.getBall();
            if (ball.getVelX() < 0) {
                // Ball is moving away, AI takes a nap ..
                return;
            }
            // Figure out linear trajectory ..
            impactDistance = width - ball.getWidth() - (int) ball.getPosX();
            impactTime = impactDistance / ((int)ball.getVelX() * ((int) (.25 * 1000)));
            targetY = (int) ball.getPosY() + ((int)ball.getVelY() * (int) (.25 * 1000)) * impactTime;

            if (Math.abs(targetY - (this.posY + this.height / 2)) < 10) {
                return;
            }

            if (targetY < this.posY + (this.height / 2)) {
                // Move up if ball is going above paddle
                speed = -speed;
            }
            if(((int)(Math.random()*5))!=1) {
                if (gameState.isEasy()) {
                    this.posY += speed / 3.3;
                } else {
                    this.posY += speed / 3;
                }
            }
            if(posY<165+ball.getHeight()/2)
            {
                posY=165+ball.getHeight()/2;
            }
            if(posY+height>578-ball.getHeight()/2)
            {
                posY=578-height-ball.getHeight()/2;
            }
        }
        else
        {
            if(posY<320)
            {
                posY+=5;
            }
            else if(posY>360)
            {
                posY-=5;
            }
        }
    }
    public void freeTheBall()
    {
        if((gameState.getBall()).isMoving()==false && gameState.getBall().getId()==1) {
            long currentTime=System.currentTimeMillis();
            if (( currentTime - time) > 1000) {
                (gameState.getBall()).setToPosition();
                gameState.reverseBallX();
                gameState.moveBall();
            }
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int)posY);
    }
    public void getTime()
    {
        time= System.currentTimeMillis();
    }
}