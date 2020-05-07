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
        info=new String[2];
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
        }
        catch (Exception e) {
            info[0]="3";
            info[1]="false";
        }
        return info;
    }
    public void setProperties(int score, boolean singlePlayer)
    {
        prop.setProperty("score", Integer.toString(score));
        prop.setProperty("singlePlayer", Boolean.toString(singlePlayer));
        try {
            output=new FileOutputStream("settings.properties");
            prop.store(output, null);
        } catch (IOException e) {}
    }
}
