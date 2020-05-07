package GameEngine;

public abstract class State{
    public State(){};
    public abstract void update();
    public abstract void render(Renderer r);
}
