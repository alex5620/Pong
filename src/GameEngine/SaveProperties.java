package GameEngine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SaveProperties {
    private Properties prop;
    private FileOutputStream output;
    private FileInputStream in;
    private String info[];
    public SaveProperties()
    {
        info=new String[3];
    }
    public String[] getSettings()
    {
        prop = new Properties();
        try {
            in=new FileInputStream("settings.properties");
            prop.load(in);
            in.close();
            info[0]=prop.getProperty("score");
            if(info[0]==null)
                info[0]="3";
            info[1]=prop.getProperty("singlePlayer");
            if(info[1]==null)
                info[1]="false";
            info[2]=prop.getProperty("isEasy");
            if(info[2]==null)
                info[2]="true";
        }
        catch (Exception e) {
            info[0]="3";
            info[1]="false";
            info[2]="true";
        }
        return info;
    }
    public void setProperties(int score, boolean singlePlayer, boolean isEasy)
    {
        prop.setProperty("score", Integer.toString(score));
        prop.setProperty("singlePlayer", Boolean.toString(singlePlayer));
        prop.setProperty("isEasy", Boolean.toString(isEasy));
        try {
            output=new FileOutputStream("settings.properties");
            prop.store(output, null);
        } catch (IOException e) {}
    }
}
