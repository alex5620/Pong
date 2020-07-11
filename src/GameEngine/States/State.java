package GameEngine.States;

import GameEngine.Graphics.Renderer;

public abstract class State{
    public State(){};
    public abstract void update();
    public abstract void render(Renderer r);
}
