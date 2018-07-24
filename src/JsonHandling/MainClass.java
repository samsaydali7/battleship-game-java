package JsonHandling;

import InfoClasses.GameInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samer2
 */
public class MainClass {
    public static void main(String[] args) {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        GameInfo gi = new GameInfo(10, "Samer", "Sameh", 13, 17, sd.format(d).toString(), sd.format(d).toString(), "Samer");
        GameInfoHandler g = new GameInfoHandler(gi);
        g.writeJsonToFile();
        
    }
}
