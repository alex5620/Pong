package GameEngine;

import java.util.ArrayList;


public class SmartPlayer extends Player {
    private float velY=5;
    private int id=1;
    private ArrayList<Integer> listY;
    private ArrayList<Integer> listX;
    private int idx;
    public SmartPlayer(GameState gameState){
        super(gameState);
        this.gameState = gameState;
        this.objectImage= gameState.getImagesLoader().getImage("player");
        this.width=objectImage.getWidth();
        this.height=objectImage.getHeight();
        super.setToInitialPosition();
        listY=new ArrayList<Integer>();
        listX=new ArrayList<Integer>();
        idx=0;
    }
    @Override
    public void update() {
        if(listY.size()>=2) {
            int newY=listY.get(listY.size()-1);
            int oldY=listY.get(listY.size()-2);
            int X=listX.get(listX.size()-1);
            if ((newY > oldY)) {
                posY += newY/100*(X/250)/2;
            }
            else if((newY < oldY))
            {
                posY -= newY/100*(X/250)/2;
            }
            else
            {
                if(X>500)
                    System.out.println("Index: "+idx);
                    if(idx==1) {
                        gameState.moveBall();
                        idx=0;
                    }
                    else
                    {
                        ++idx;
                        if((posY+height/2)<260)
                        {
                            posY+=Math.abs(velY);
                        }
                        else
                        {
                            if((posY+height/2)>460)
                            {
                                posY-=Math.abs(velY);
                            }
                        }
                    }
            }
        }
        checkForBoundaries();
    }
    @Override
    public void render(Renderer r) {
        r.drawImage(objectImage, (int)posX,(int)posY);
    }
    public void addValues(int yValue, int xValue) {
        listY.add(yValue);
        if (listY.size() > 2)
            listY.remove(0);
        listX.add(xValue);
        if (listX.size() > 2)
            listX.remove(0);
    }
}