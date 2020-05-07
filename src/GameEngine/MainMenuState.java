package GameEngine;


public class MainMenuState extends State {
    private GameEngine gameEngine;
    public MainMenuState(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    }
    public void render(Renderer r)
    {
        r.drawImage(gameEngine.getImagesLoader().getImage("mainmenu"), 0, 0);
        if(checkIfPlay())
        {
            for(int i=0;i<5;++i)
                r.drawRect(410-i,240-i,186+i*2, 72+i*2, 0xffC4C4C4);
        }
        if(checkIfSettings())
        {
            for(int i=0;i<5;++i)
                r.drawRect(340-i,360-i,330+i*2, 65+i*2, 0xffC4C4C4);
        }
        if(checkIfExit())
        {
            for(int i=0;i<5;++i)
                r.drawRect(420-i,465-i,165+i*2, 65+i*2, 0xffC4C4C4);
        }
    }
    public void update()
    {
        if(gameEngine.getMouseInput().isClick1Up()) {
            if (checkIfPlay()) {
                gameEngine.setState(GameEngine.geState.GameState);
            }
            if(checkIfSettings())
            {
                gameEngine.setState(GameEngine.geState.SettingsMenuState);
            }
            if(checkIfExit())
            {
                gameEngine.getWindow().closeWindow();
            }
        }
    }
    public boolean checkIfPlay() {
        int x = gameEngine.getMouseInput().getX();
        int y = gameEngine.getMouseInput().getY();
        if (x > 405 && x < 601 && y > 235 && y < 317) {
            return true;
        }
        return false;
    }
    public boolean checkIfSettings() {
        int x = gameEngine.getMouseInput().getX();
        int y = gameEngine.getMouseInput().getY();
        if (x > 335 && x < 675 && y > 355 && y < 430) {
            return true;
        }
        return false;
    }
    public boolean checkIfExit()
    {
        int x = gameEngine.getMouseInput().getX();
        int y = gameEngine.getMouseInput().getY();
        if (x > 415 && x < 590 && y > 460 && y < 535) {
            return true;
        }
        return false;
    }
}