package GameEngine;


public abstract class GameObject {
    protected Image objectImage;
    protected GameState gameState;
    protected float posX, posY;
    protected int width, height;
    public abstract void update();
    public abstract void render(Renderer r);
    public float getPosX() { return posX; }
    public float getPosY() { return posY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
