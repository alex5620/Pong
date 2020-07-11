package GameEngine.States;

import GameEngine.GameEngine;
import GameEngine.Graphics.Renderer;
import GameEngine.Sound;

public class MainMenuState extends State {
    private GameEngine gameEngine;
    private boolean hover;
    public MainMenuState(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        hover=false;
    }
    public void render(Renderer r)
    {
        r.drawImage(gameEngine.getImagesLoader().getImage("mainmenu"), 0, 0);
        if(Sound.isSoundEnabled())
            r.drawImage(gameEngine.getImagesLoader().getImage("volumeOn"), 900, 500);
        else
            r.drawImage(gameEngine.getImagesLoader().getImage("volumeOff"), 900, 500);
        if(checkIfPlay())
        {
            for(int i=0;i<5;++i)
                r.drawRect(410-i,240-i,186+i*2, 72+i*2, 0xffC4C4C4);
            playHoverSound();
            hover=true;
        }
        else if(checkIfSettings())
        {
            for(int i=0;i<5;++i)
                r.drawRect(340-i,360-i,330+i*2, 65+i*2, 0xffC4C4C4);
            playHoverSound();
            hover=true;
        }
        else if(checkIfExit())
        {
            for(int i=0;i<5;++i)
                r.drawRect(420-i,465-i,165+i*2, 65+i*2, 0xffC4C4C4);
            playHoverSound();
            hover=true;
        }
        else
        {
            hover=false;
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
            if(checkIsSoundEnabled())
            {
                Sound.reverseSoundSettings();
                gameEngine.getSoundsLoader().getSound("buttonpressed").play();
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
    public boolean checkIsSoundEnabled()
    {
        int x = gameEngine.getMouseInput().getX();
        int y = gameEngine.getMouseInput().getY();
        if (x > 920 && x < 1017 && y > 530 && y < 628) {
            return true;
        }
        return false;
    }
    public void playHoverSound() {
        if (hover == false) {
            gameEngine.getSoundsLoader().getSound("hover").play();
        }
    }
}