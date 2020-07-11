package GameEngine.States;

import GameEngine.GameEngine;
import GameEngine.Graphics.Renderer;

public class SettingsMenuState extends State {
    private GameEngine gameEngine;
    private boolean singlePlayer;
    private boolean isEasy;
    private int score;
    public SettingsMenuState(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        getSettings();
    }
    public void render(Renderer r)
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        r.drawImage(gameEngine.getImagesLoader().getImage("settingsmenu"), 0, 0 );
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 250, 216);
        r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 250, 433);
        if(singlePlayer) {
            r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 250, 216);
            r.drawImage(gameEngine.getImagesLoader().getImage("simple"), 400, 370);
            r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 400, 480);
            r.drawImage(gameEngine.getImagesLoader().getImage("unchecked"), 522, 480);
            if(isEasy)
            {
                r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 400, 480);
            }
            else
            {
                r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 522, 480);
            }
        }
        else
        {
            r.drawImage(gameEngine.getImagesLoader().getImage("checked"), 250, 433);
        }
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
        if(checkIfBack(x,y))
        {
            for(int i=0;i<5;++i)
                r.drawRect(895-i,570-i,100+i*2, 40+i*2, 0xffC4C4C4);
        }
    }
    public void update()
    {
        if(gameEngine.getMouseInput().isClick1Up()) {
            int x=gameEngine.getMouseInput().getX();
            int y=gameEngine.getMouseInput().getY();
            if (checkIfSinglePlayerCheckBox(x, y)) {
                singlePlayer = true;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if (checkIfMultiplayerPlayerCheckBox(x,y)) {
                singlePlayer = false;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfFirstTo3(x, y))
            {
                score=3;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfFirstTo5(x,y))
            {
                score=5;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfFirstTo9(x,y))
            {
                score=9;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfEasy(x, y))
            {
                isEasy=true;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfHard(x, y))
            {
                isEasy=false;
                gameEngine.getSoundsLoader().getSound("checkbox").play();
            }
            if(checkIfBack(x,y))
            {
                gameEngine.setSettings(score, singlePlayer, isEasy);
                gameEngine.setState(GameEngine.geState.MainMenuState);
                gameEngine.getSoundsLoader().getSound("buttonpressed").play();
            }
        }
    }
    public boolean checkIfSinglePlayerCheckBox(int x, int y)
    {

        if(x > 250 && x < 310 && y > 216 && y < 276)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfMultiplayerPlayerCheckBox(int x, int y)
    {
        if(x > 250 && x < 310 && y > 433 && y < 493)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo3(int x, int y)
    {
        if(x > 750 && x < 810 && y > 283 && y < 343)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo5(int x, int y)
    {
        if(x > 750 && x < 810 && y > 378 && y < 442)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfFirstTo9(int x, int y)
    {
        if(x > 750 && x < 810 && y > 478 && y < 542)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfEasy(int x, int y)
    {
        if(x> 400 && x<460 && y > 480 && y <546 )
        {
            return true;
        }
        return false;
    }
    public boolean checkIfHard(int x, int y)
    {
        if(x> 522 && x<586 && y > 480 && y <546 )
        {
            return true;
        }
        return false;
    }
    public boolean checkIfBack(int x, int y)
    {
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
        isEasy=Boolean.parseBoolean(info[2]);
    }
}