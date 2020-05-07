package GameEngine;
import java.awt.Rectangle;

public class Collider {
    private GameState gameState;
    public Collider(GameState gameState) {
        this.gameState = gameState;
    }
    public void update() {
        for(GameObject obj: gameState.getObjects())
        {
            if(obj instanceof Ball)
            {
                for(GameObject player: gameState.getObjects())
                {
                    if(player instanceof Player)
                    {
                        Rectangle ballRect=new Rectangle((int)obj.getPosX(), (int)obj.getPosY(), obj.getWidth(), obj.getHeight());
                        Rectangle playerRect=new Rectangle((int)player.getPosX(), (int)player.getPosY(), player.getWidth(), player.getHeight());
                        if(ballRect.intersects(playerRect))
                        {
                            ((Ball)obj).reverseVelX();
                        }
                    }
                }
            }
        }
    }
}
