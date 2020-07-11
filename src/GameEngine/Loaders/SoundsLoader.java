package GameEngine.Loaders;
import GameEngine.Sound;

import java.util.HashMap;

public class SoundsLoader {
    private HashMap<String , Sound> sounds;
    public SoundsLoader() {
        sounds=new HashMap<>();
        sounds.put("ball_player_hit",new Sound("/Assets/ball_player_hit.wav").setVolume(4));
        sounds.put("checkbox",new Sound("/Assets/checkbox.wav").setVolume(-14));
        sounds.put("wall",new Sound("/Assets/wall.wav").setVolume(6));
        sounds.put("background",new Sound("/Assets/background.wav").setVolume(-10));
        sounds.put("hover",new Sound("/Assets/hover.wav").setVolume(6));
        sounds.put("buttonpressed",new Sound("/Assets/buttonpressed.wav").setVolume(-10));
        sounds.put("score",new Sound("/Assets/score.wav"));
        sounds.put("cscore",new Sound("/Assets/cscore.wav").setVolume(-6));
        sounds.put("won",new Sound("/Assets/won.wav").setVolume(-6));
        sounds.put("gameover",new Sound("/Assets/gameover.wav"));
    }
    public Sound getSound(String sound)
    {
        return sounds.get(sound);
    }
}
