package GameEngine.Graphics;

import GameEngine.GameEngine;
import GameEngine.Graphics.Image;

public class ImageRequest {
    private Image image;
    private int offX, offY;
    public ImageRequest(Image image, int offX, int offY)
    {
        this.image=image;
        this.offX=offX;
        this.offY=offY;
    }
    public Image getImage() {
        return image;
    }
    public int getOffX() {
        return offX;
    }
    public int getOffY() {
        return offY;
    }
}