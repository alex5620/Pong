package GameEngine;

import java.util.ArrayList;

public class Game {
    private GameEngine gameEngine;
    private ArrayList<GameObject> objects;
    private Renderer renderer;
    private Collider collider;
    private int score1;
    private int score2;
    public Game(GameEngine engine)
    {
        this.gameEngine=engine;
        objects=new ArrayList<>();
        renderer=engine.getRenderer();
        collider = new Collider(this);
        objects.add(new Player(this));
        objects.add(new Player(this));
        objects.add(new Ball(this));
        score1=0;
        score2=0;
    }
    public void update(float dt)
    {
        for(GameObject obj:objects)
        {
            obj.update(dt);
        }
        collider.update();
    }
    public void render() {
        renderer.drawImage(gameEngine.getImagesLoader().getBackgroundImage(), 0, 0 );
        for(GameObject obj: objects)
        {
            obj.render(renderer);
        }
        renderer.drawImage(gameEngine.getImagesLoader().getNumberImage(score1), 140, 80 );
        renderer.drawImage(gameEngine.getImagesLoader().getNumberImage(score2), 830, 80 );
    }
    public KeyboardInput getKeyboardInput() { return gameEngine.getKeyboardInput(); }
    public MouseInput getMouseInput() { return gameEngine.getMouseInput(); }
    public ImagesLoader getImagesLoader()
    {
        return gameEngine.getImagesLoader();
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
    public void increaseScore1()
    {
        ++score1;
        score1%=10;
    }
    public void increseScore2()
    {
        ++score2;
        score2%=10;
    }
}
