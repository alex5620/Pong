package GameEngine.States;

import GameEngine.GameEngine;
import GameEngine.GameObjects.*;
import GameEngine.Collider;
import GameEngine.Graphics.ImageTile;
import GameEngine.Graphics.Renderer;
import GameEngine.Input.KeyboardInput;
import GameEngine.Input.MouseInput;
import GameEngine.Loaders.ImagesLoader;
import GameEngine.Loaders.SoundsLoader;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class GameState extends State {
    private GameEngine gameEngine;
    private ArrayList<GameObject> objects;
    private Collider collider;
    private int score1;
    private int score2;
    private int firstTo;
    private boolean isPause;
    private boolean singlePlayer;
    private boolean isEasy;
    private boolean losingSoundPlayed;
    public GameState(GameEngine engine)
    {
        this.gameEngine=engine;
        objects=new ArrayList<>();
        collider = new Collider(this);
        objects.add(new Player(this));
        getSettings();
        if(singlePlayer)
        {
            objects.add(new SmartPlayer(this));
        }
        else {
            objects.add(new Player(this));
        }
        objects.add(new Ball(this));
        score1=0;
        score2=0;
        isPause=false;
        gameEngine.getSoundsLoader().getSound("background").loop();
    }
    public void update() {
        if(checkIfWon()==false) {
            if (getKeyboardInput().isKeyUp(KeyEvent.VK_P)) {
                isPause = !isPause;
                if (isPause == true) {
                    gameEngine.getSoundsLoader().getSound("background").stop();
                }
                else
                {
                    gameEngine.getSoundsLoader().getSound("background").play();
                }
            }
        }
        if (isPause==false) {
            for (GameObject obj : objects) {
                if(!checkIfWon() )
                    obj.update();
            }
            collider.update();
            if(checkIfWon()) {
                gameEngine.getSoundsLoader().getSound("background").stop();
                if(singlePlayer==true && firstTo==score2) {
                    if (losingSoundPlayed == false) {
                        gameEngine.getSoundsLoader().getSound("gameover").play();
                        losingSoundPlayed=true;
                    }
                }
                else
                {
                    gameEngine.getSoundsLoader().getSound("won").play();
                }
                if(singlePlayer==true && score1==firstTo)
                {
                    getSmartPlayer().getTime();
                }
                if (getMouseInput().isClick1Up()) {
                    if (checkIfBackAfterSomeoneWon()) {
                        gameEngine.setState(GameEngine.geState.MainMenuState);
                        Player.resetInstanes();
                        gameEngine.getSoundsLoader().getSound("buttonpressed").play();
                        gameEngine.getSoundsLoader().getSound("won").stop();
                        gameEngine.getSoundsLoader().getSound("gameover").stop();
                    }
                    if (checkIfPlayAgain()) {
                        playAgain();
                        setPlayersToInitial();
                        gameEngine.getSoundsLoader().getSound("background").play();
                        gameEngine.getSoundsLoader().getSound("won").stop();
                        gameEngine.getSoundsLoader().getSound("gameover").stop();
                    }
                }
                for(GameObject obj :objects) {
                    if (obj instanceof Ball) {
                        ((Ball) obj).setMoving(false);
                    }
                }
            }
        }
        else {
            if(getMouseInput().isClick1Up()) {
                if (checkIfBack()) {
                    gameEngine.setState(GameEngine.geState.MainMenuState);
                    Player.resetInstanes();
                    gameEngine.getSoundsLoader().getSound("buttonpressed").play();
                }
            }
        }
    }
    public void render(Renderer r) {
        r.drawImage(gameEngine.getImagesLoader().getImage("background"), 0, 0);
        for(GameObject obj: objects) {
            obj.render(r);
        }
        r.drawImageTile((ImageTile) gameEngine.getImagesLoader().getImage("digits"), 140, 80, score1%5, score1/5);
        r.drawImageTile((ImageTile) gameEngine.getImagesLoader().getImage("digits"), 830, 80, score2%5, score2/5);
        if(isPause) {
            r.drawImage(gameEngine.getImagesLoader().getImage("pause"), 300, 280);
            r.drawImage(gameEngine.getImagesLoader().getImage("back"), 920, 590);
            getBall().render(r);
            if(checkIfBack())
            {
                for (int i = 0; i < 3; ++i)
                    r.drawRect(915 - i, 585 - i, 100 + i * 2, 40 + i * 2, 0xffC4C4C4);
            }
        }
        else
        {
            if(checkIfWon()) {
                r.drawImage(gameEngine.getImagesLoader().getImage("someoneWon"), 300, 226);
                if(singlePlayer && firstTo==score2) {
                    r.drawImage(gameEngine.getImagesLoader().getImage("computer"), 343, 274);
                }
                else {
                    if (score1 == firstTo) {
                        r.drawFillRect(515, 292, 3, 26, 0xffC4C4C4);
                    } else {
                        r.drawFillRect(508, 292, 3, 26, 0xffC4C4C4);
                        r.drawFillRect(520, 292, 3, 26, 0xffC4C4C4);
                    }
                }
                if(checkIfBackAfterSomeoneWon()) {
                    for (int i = 0; i < 3; ++i) {
                        r.drawRect(461 - i, 440 - i, 100 + i * 2, 45 + i * 2, 0xffC4C4C4);
                    }
                }
                if(checkIfPlayAgain())
                {
                    for (int i = 0; i < 3; ++i) {
                        r.drawRect(403 - i, 363 - i, 210 + i * 2, 45 + i * 2, 0xffC4C4C4);
                    }
                }
            }
        }
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
        gameEngine.getSoundsLoader().getSound("score").play();
    }
    public void increaseScore2()
    {
        ++score2;
        score2%=10;
        if(singlePlayer)
        {
            gameEngine.getSoundsLoader().getSound("cscore").play();
        }
        else
        {
            gameEngine.getSoundsLoader().getSound("score").play();
        }
    }
    public GameObject getPlayer(int id)
    {
        GameObject player=null;
        for(GameObject obj:objects)
        {
            if(obj instanceof Player && ((Player)obj).getId()==id)
            {
                player=obj;
                break;
            }
        }
        return player;
    }
    public Ball getBall()
    {
        Ball ball=null;
        for(GameObject obj:objects)
        {
            if(obj instanceof Ball)
            {
                ball=(Ball)obj;
                break;
            }
        }
        return ball;
    }
    public boolean checkIfWon()
    {
        if(score1==firstTo || score2==firstTo)
        {
            return true;
        }
        return false;
    }
    public void getSettings()
    {
        String info[]=gameEngine.getSettings();
        firstTo=Integer.parseInt(info[0]);
        singlePlayer=Boolean.parseBoolean(info[1]);
        isEasy= Boolean.parseBoolean(info[2]);
    }
    public boolean checkIfBack()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 910 && x < 1020 && y > 580 && y < 630)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfBackAfterSomeoneWon()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 462 && x < 570 && y > 442 && y < 496)
        {
            return true;
        }
        return false;
    }
    public boolean checkIfPlayAgain()
    {
        int x=gameEngine.getMouseInput().getX();
        int y=gameEngine.getMouseInput().getY();
        if(x > 403 && x < 623 && y > 365 && y < 418)
        {
            return true;
        }
        return false;
    }
    public void playAgain()
    {
        getBall().setMoving(false);
        score1=0;
        score2=0;
    }
    public void setPlayersToInitial() {
        for (GameObject obj : objects) {
            if (obj instanceof Player) {
                ((Player) obj).setToInitialPosition();
            }
            if (obj instanceof Ball) {
                ((Ball) obj).setMoving(true);
            }
        }
    }
    public void moveBall()
    {
        for (GameObject obj : objects) {
            if (obj instanceof Ball) {
                ((Ball) obj).setMoving(true);
            }
        }
    }
    public void reverseBallX()
    {
        for (GameObject obj : objects) {
            if (obj instanceof Ball) {
                ((Ball) obj).reverseVelX();
            }
        }
    }
    public SmartPlayer getSmartPlayer()
    {
        for(Object obj:objects)
        {
            if(obj instanceof SmartPlayer)
            {
                return (SmartPlayer) obj;
            }
        }
        return null;
    }
    public boolean isEasy()
    {
        return isEasy;
    }
    public boolean isSinglePlayer()
    {
        return singlePlayer;
    }
    public SoundsLoader getSoundsLoader()
    {
        return gameEngine.getSoundsLoader();
    }
}
