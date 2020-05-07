package GameEngine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameState extends State{
    private GameEngine gameEngine;
    private ArrayList<GameObject> objects;
    private Collider collider;
    private int score1;
    private int score2;
    private int firstTo;
    private boolean isPause;
    private boolean singlePlayer;
    public GameState(GameEngine engine)
    {
        this.gameEngine=engine;
        objects=new ArrayList<>();
        collider = new Collider(this);
        objects.add(new Player(this));
        objects.add(new Player(this));
        objects.add(new Ball(this));
        score1=0;
        score2=0;
        isPause=false;
        getSettings();
    }
    public void update() {
        if(checkIfWon()==false) {
            if (getKeyboardInput().isKeyUp(KeyEvent.VK_P)) {
                isPause = !isPause;
            }
        }
        if (isPause==false) {
            for (GameObject obj : objects) {
                if( !(obj instanceof SmartPlayer) || !checkIfWon() )
                    obj.update();
                if(obj instanceof Ball)
                {
                    for(GameObject smartPlayer : objects)
                    {
                        if(smartPlayer instanceof SmartPlayer)
                        {
                            ((SmartPlayer)smartPlayer).addValues((int)obj.getPosY(), (int)obj.getPosX());
                        }
                    }
                }
            }
            collider.update();
            if(checkIfWon()) {
                if (getMouseInput().isClick1Up()) {
                    if (checkIfBackAfterSomeoneWon()) {
                        gameEngine.setState(GameEngine.geState.MainMenuState);
                        Player.resetInstanes();
                    }
                    if (checkIfPlayAgain()) {
                        playAgain();
                        setPlayersToInitial();
                    }
                }
                for(GameObject obj :objects)
                {
                    if(obj instanceof Ball)
                    {
                        ((Ball)obj).setMoving(false);
                    }
                }
            }
        }
        else {
            if(getMouseInput().isClick1Up()) {
                if (checkIfBack()) {
                    gameEngine.setState(GameEngine.geState.MainMenuState);
                    Player.resetInstanes();
                }
            }
        }
    }
    public void render(Renderer r) {
        r.drawImage(gameEngine.getImagesLoader().getImage("background"), 0, 0);
        for(GameObject obj: objects)
        {
            obj.render(r);
        }
        r.drawImage(gameEngine.getImagesLoader().getImage(Integer.toString(score1)), 140, 80 );
        r.drawImage(gameEngine.getImagesLoader().getImage(Integer.toString(score2)), 830, 80 );
        if(isPause) {
            r.drawImage(gameEngine.getImagesLoader().getImage("pause"), 300, 280);
            r.drawImage(gameEngine.getImagesLoader().getImage("back"), 920, 590);
            if(checkIfBack())
            {
                for (int i = 0; i < 3; ++i)
                    r.drawRect(915 - i, 585 - i, 100 + i * 2, 40 + i * 2, 0xffC4C4C4);
            }
        }
        else
        {
            if(checkIfWon()) {
                r.drawImage(gameEngine.getImagesLoader().getImage("someoneWon"), 300, 225);
                if(score1==firstTo) {
                    r.drawFillRect(515, 292, 3, 26, 0xffC4C4C4);
                }
                else
                {
                    r.drawFillRect(508, 292, 3, 26, 0xffC4C4C4);
                    r.drawFillRect(520, 292, 3, 26, 0xffC4C4C4);
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
    }
    public void increseScore2()
    {
        ++score2;
        score2%=10;
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
}
