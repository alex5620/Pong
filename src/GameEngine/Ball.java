package GameEngine;

public class Ball extends GameObject {
    private int velX=1;
    private int velY=1;
    public Ball(Game game) {
        this.game = game;
        this.objectImage = game.getImagesLoader().getBallImage();
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        setToInitialPosition();
    }
    @Override
    public void update(float dt) {
        System.out.println("Vel: "+velY);
            posX =posX+(dt * velX * 400);
            posY += (dt * velY * 400);
        if(posY<165)
        {
            reverseVelY();
        }
        if(posY+height>578)
        {
            reverseVelY();
        }
        if(posX<105)
        {
            game.increseScore2();
            reverseVelX();
        }
        if(posX>906-width)
        {
            game.increaseScore1();
            reverseVelX();
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int) posY);
    }
    private void checkForBoundaries() {

    }
    public void setToInitialPosition()//mingea este pozitionata deasupra player-ului
    {
        posX=400;
        posY=300;
    }
    public void reverseVelX()
    {
        velX*=-1;
    }
    public void reverseVelY()
    {
        velY*=-1;
    }
}