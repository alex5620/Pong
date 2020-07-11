package GameEngine;
import GameEngine.GameObjects.Ball;
import GameEngine.GameObjects.GameObject;
import GameEngine.GameObjects.Player;
import GameEngine.States.GameState;

import java.awt.Rectangle;

public class Collider {
    private GameState gameState;
    private boolean paddleBallDetectionX=false;
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
                        if((paddleBallDetectionX==false) && ballRect.intersects(playerRect))
                        {
                            something((Ball)obj, (Player)player);
                            paddleBallDetectionX=true;
                            gameState.getSoundsLoader().getSound("ball_player_hit").play();
                        }
                        else
                        {
                            if(obj.getPosX()>400 && obj.getPosX()<500)
                                paddleBallDetectionX=false;
                        }
                    }
                }
            }
        }
    }
     public void something(Ball m_ball, Player m_paddle) {
        double ballHeight = m_ball.getHeight();
        double ballCenterY = m_ball.getPosY() + ballHeight / 2;
        double paddleHeight = m_paddle.getHeight();
        double paddleCenterY = m_paddle.getPosY() + paddleHeight / 2;
        double speedX = m_ball.getVelX();
        double speedY = m_ball.getVelY();

        // Applying the Pythagorean theorem, calculate the ball's overall
        // speed from its X and Y components.  This will always be a
        // positive value.
        double speedXY = Math.sqrt(speedX * speedX + speedY * speedY);

        // Calculate the position of the ball relative to the center of
        // the paddle, and express this as a number between -1 and +1.
        // (Note: collisions at the ends of the paddle may exceed this
        // range, but that is fine.)
        double posY = (ballCenterY - paddleCenterY) / (paddleHeight / 2);
         //System.out.println(posY);
        // Define an empirical value (tweak as needed) for controlling
        // the amount of influence the ball's position against the paddle
        // has on the X speed.  This number must be between 0 and 1.
        final double influenceY = 0.75;

        // Let the new X speed be proportional to the ball position on
        // the paddle.  Also make it relative to the original speed and
        // limit it by the influence factor defined above.
        speedY = speedXY * posY * influenceY;
        m_ball.setSpeedY(speedY);

        // Finally, based on the new X speed, calculate the new Y speed
        // such that the new overall speed is the same as the old.  This
        // is another application of the Pythagorean theorem.  The new
        // Y speed will always be nonzero as long as the X speed is less
        // than the original overall speed.
        speedX = Math.sqrt(speedXY * speedXY - speedY * speedY) *
                (speedX > 0 ? -1 : 1);
        m_ball.setSpeedX(speedX);
    }
    /*public void something(Ball m_ball, Player m_paddle) {
        double ballWidth = m_ball.getWidth();
        double ballCenterX = m_ball.getPosX() + ballWidth / 2;
        double paddleWidth = m_paddle.getWidth();
        double paddleCenterX = m_paddle.getPosX() + paddleWidth / 2;
        double speedX = m_ball.getVelX();
        double speedY = m_ball.getVelY();

        // Applying the Pythagorean theorem, calculate the ball's overall
        // speed from its X and Y components.  This will always be a
        // positive value.
        double speedXY = Math.sqrt(speedX * speedX + speedY * speedY);

        // Calculate the position of the ball relative to the center of
        // the paddle, and express this as a number between -1 and +1.
        // (Note: collisions at the ends of the paddle may exceed this
        // range, but that is fine.)
        double posX = (ballCenterX - paddleCenterX) / (paddleWidth / 2);

        // Define an empirical value (tweak as needed) for controlling
        // the amount of influence the ball's position against the paddle
        // has on the X speed.  This number must be between 0 and 1.
        final double influenceX = 0.75;

        // Let the new X speed be proportional to the ball position on
        // the paddle.  Also make it relative to the original speed and
        // limit it by the influence factor defined above.
        speedX = speedXY * posX * influenceX;
        m_ball.setSpeedX((int)speedX);

        // Finally, based on the new X speed, calculate the new Y speed
        // such that the new overall speed is the same as the old.  This
        // is another application of the Pythagorean theorem.  The new
        // Y speed will always be nonzero as long as the X speed is less
        // than the original overall speed.
        speedY = Math.sqrt(speedXY * speedXY - speedX * speedX) *
                (speedY > 0 ? -1 : 1);
        m_ball.setSpeedY((int)speedY);
    }*/
}
