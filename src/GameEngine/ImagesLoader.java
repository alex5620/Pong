package GameEngine;

import java.util.HashMap;

public class ImagesLoader {
    private HashMap<String,Image> images;
    public ImagesLoader()
    {
        images=new HashMap<String, Image>();
        images.put("background", new Image("/Assets/background.png"));
        images.put("ball", new Image("/Assets/ball.png"));
        images.put("player", new Image("/Assets/player.png"));
        for(int i=0;i<10;++i)
        {
            images.put(Integer.toString(i),new Image("/Assets/"+i+".png"));
        }
        images.put("settingsmenu", new Image("/Assets/settingsmenu.png"));
        images.put("checked", new Image("/Assets/checked.png"));
        images.put("unchecked", new Image("/Assets/unchecked.png"));
        images.put("mainmenu", new Image("/Assets/mainmenu.png"));
        images.put("simple", new Image("/Assets/simple.png"));
        images.put("pause", new Image("/Assets/pause.png"));
        images.put("back", new Image("/Assets/back.png"));
        images.put("someoneWon", new Image("/Assets/someoneWon.png"));
    }
    public Image getImage(String key)
    {
        return images.get(key);
    }
}
