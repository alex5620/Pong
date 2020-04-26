package GameEngine;

import GameEngine.Game;
import GameEngine.Renderer;

public abstract class GameObject {
    protected Image objectImage;
    protected Game game;
    protected float posX, posY;
    protected int width, height;
    public abstract void update(float dt);
    public abstract void render(Renderer r);
    public float getPosX() { return posX; }
    public float getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
