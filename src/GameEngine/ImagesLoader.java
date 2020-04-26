package GameEngine;
import GameEngine.Image;

public class ImagesLoader {
    private Image backgroundImage;
    private Image ballImage;
    private Image playerImage;
    private Image []numberImage;
    public ImagesLoader()
    {
        backgroundImage=new Image("/Assets/background.png");
        ballImage=new Image("/Assets/ball.png");
        playerImage=new Image("/Assets/player.png");
        numberImage=new Image[10];
        for(int i=0;i<10;++i)
        {
            numberImage[i]=new Image("/Assets/"+i+".png");
        }
    }
    public Image getBackgroundImage() {
        return backgroundImage;
    }
    public Image getBallImage()
    {
        return ballImage;
    }
    public Image getPlayerImage()
    {
        return playerImage;
    }
    public Image getNumberImage(int index)
    {
        return numberImage[index];
    }
}
