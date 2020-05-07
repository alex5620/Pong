package GameEngine;

import java.util.Properties;

public class SettingsMenuState extends State {
    private GameEngine gameEngine;
    private boolean singlePlayer;
    private int score;
    public SettingsMenuState(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        singlePlayer=true;
        score=3;
        getSettings();
    }
    public void render(Renderer r)
    {
        r.drawImage(gameEngine.getImagesLoader().getImage("settingsmenu"), 0, 0 );
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 250, 216);
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 250, 433);
        if(singlePlayer) {
            r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 250, 216);
            r.drawImage(gameEngine.getImagesLoader().getImage("simple"), 400, 370);
        }
        else
        {
            r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 250, 433);
        }
        //score
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 750, 283);
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 750, 378);
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 750, 478);
        switch (score)
        {
            case 3:
                r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 750, 283);
                break;
            case 5:
                r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 750, 378);
                break;
            case 9:
                r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 750, 478);
                break;
        }
        if(checkIfBack())
        {
            for(int i=0;i<5;++i)
                r.drawRect(895-i,570-i,100+i*2, 40+i*2, 0xffC4C4C4);
        }
    }
    public void update()
    {
        if(gameEngine.getMouseInput().isClick1Up()) {
            if (checkIfSinglePlayerCheckBox()) {
                singlePlayer = true;
            }
            if (checkIfMultiplayerPlayerCheckBox()) {
                singlePlayer = false;
            }
            if(checkIfFirstTo3())
            {
                score=3;
            }
            if(checkIfFirstTo5())
            {
                score=5;
            }
            if(checkIfFirstTo9())
            {
                score=9;
            }
            if(checkIfBack())
            {
                gameEngine.setSettings(score, singlePlayer);
                gameEngine.setState(GameEngine.geState.MainMenuState);
            }
        }
    }
    public boolean checkIfSinglePlayerCheckBox()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 250 && x < 310 && y > 216 && y < 276)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfMultiplayerPlayerCheckBox()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 250 && x < 310 && y > 433 && y < 493)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo3()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 750 && x < 810 && y > 283 && y < 343)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo5()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 750 && x < 810 && y > 378 && y < 442)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo9()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 750 && x < 810 && y > 478 && y < 542)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfBack()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 895 && x < 995 && y > 570 && y < 610)
        {
            return true;
        }
        return false;
    }
    public void getSettings() {
        String info[]=gameEngine.getSettings();
        score=Integer.parseInt(info[0]);
        singlePlayer=Boolean.parseBoolean(info[1]);
    }
}