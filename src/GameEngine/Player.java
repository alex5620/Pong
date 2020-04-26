package GameEngine;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public static int instances=0;
    private float velY=5;
    private int id;
    public Player(Game game){
        id=instances;
        ++instances;
        this.game = game;
        this.objectImage=game.getImagesLoader().getPlayerImage();
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        if(id==0)
        {
            posX=115;
            posY=335;
        }
        else
        {
            posX=885;
            posY=335;
        }
    }
    @Override
    public void update(float dt) {
        if(id==1) {
            if (game.getKeyboardInput().isKey(KeyEvent.VK_RIGHT)) {//update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
            if (game.getKeyboardInput().isKey(KeyEvent.VK_LEFT)) { //update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
        }
        else
        {
            if (game.getKeyboardInput().isKey(KeyEvent.VK_D)) {//update pozitie jucator
                posY += velY;
                checkForBoundaries();
            }
            if (game.getKeyboardInput().isKey(KeyEvent.VK_A)) { //update pozitie jucator
                posY -= velY;
                checkForBoundaries();
            }
        }
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int)posY);
    }                                                 //dimensiunea player-ului
    private void checkForBoundaries()            //se verifica limita din stanga si din dreapta a ferestrei
    {
        if(posY<165)
        {
            posY=165;
        }
        if(posY+height>578)
        {
            System.out.println("Yes");
            posY=578-height;
        }
    }
}