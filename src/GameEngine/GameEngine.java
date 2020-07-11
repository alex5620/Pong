package GameEngine;

import GameEngine.Graphics.Renderer;
import GameEngine.Input.KeyboardInput;
import GameEngine.Input.MouseInput;
import GameEngine.Loaders.ImagesLoader;
import GameEngine.Loaders.SoundsLoader;
import GameEngine.States.GameState;
import GameEngine.States.MainMenuState;
import GameEngine.States.SettingsMenuState;
import GameEngine.States.State;

import static java.lang.Thread.sleep;

public class GameEngine implements Runnable {
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private KeyboardInput keyboardInput;
    private MouseInput mouseInput;
    private ImagesLoader imagesLoader;
    private SoundsLoader soundsLoader;
    private State currentState;
    private SaveProperties properties;
    private boolean running;
    private final double UPDATE_CAP = 1.0 / 60.0;
    private final int width = 1024, height = 640;
    private final String title = "PONG";
    public enum geState{ MainMenuState, SettingsMenuState, GameState};
    public GameEngine() {
        running = false;
        thread = new Thread(this);
        window = new Window(this);
        renderer = new Renderer(this);
        imagesLoader= new ImagesLoader();
        soundsLoader= new SoundsLoader();
        keyboardInput = new KeyboardInput(this);
        mouseInput=new MouseInput(this);
        currentState=new MainMenuState(this);;
        properties=new SaveProperties();
    }

    private void update() {
        currentState.update();
        keyboardInput.update();
        mouseInput.update();
    }
    private void render() {
        renderer.process();
        currentState.render(renderer);
        window.update();
    }
    public void run() {
        running = true;
        boolean Render;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;//system time
        double passedTime = 0;
        double unprocessedTime = 0;

        int frames = 0;
        int fps = 0;
        double frameTime = 0;

        while (running) {
            Render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;//timp nou-timp vechi
            lastTime = firstTime;

            unprocessedTime += passedTime;//se aduna diferenta de mai sus
            frameTime += passedTime;
            while (unprocessedTime >= UPDATE_CAP)//daca depaseste a 60-a parte din 1 atunci facem update
            {
                unprocessedTime -= UPDATE_CAP;
                Render = true;
                update();
                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    //System.out.println("Fps: " + fps);
                }
            }
            if (Render) {
                render();
                ++frames;
            } else {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setState(geState state)
    {
        switch (state) {
            case GameState:
                currentState = new GameState(this);
                break;
            case MainMenuState:
                currentState = new MainMenuState(this);
                break;
            case SettingsMenuState:
                currentState= new SettingsMenuState(this);
                break;
        }
    }
    public synchronized void start() {
        thread.run();
    }
    public synchronized void stop() {
        running = false;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public String getTitle() {
        return title;
    }
    public Window getWindow() {
        return window;
    }
    public ImagesLoader getImagesLoader() { return imagesLoader; }
    public SoundsLoader getSoundsLoader() { return soundsLoader; }
    public KeyboardInput getKeyboardInput() { return keyboardInput; }
    public MouseInput getMouseInput() { return mouseInput; }
    public String[] getSettings()
    {
        return properties.getSettings();
    }
    public void setSettings(int score, boolean singlePlayer, boolean isEasy)
    {
        properties.setProperties(score, singlePlayer, isEasy);
    }
}