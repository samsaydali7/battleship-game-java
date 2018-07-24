/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonHandling;

import InfoClasses.PlayerInfo;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import json.JSONArray;
import json.JSONObject;
import json.JSONTokener;

/**
 *
 * @author Samer2
 */
public class PlayerInfoHandler implements IJHandler{
    private PlayerInfo playerInfo;

    public PlayerInfoHandler(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }
    
    @Override
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        json.put("playerID", playerInfo.getPlayerID());
        json.put("playerName", playerInfo.getPlayerName());
        json.put("playedGames", playerInfo.getPlayedGames());
        json.put("mark", playerInfo.getMark());
        return json;
    }

    @Override
    public void writeJsonToFile() {
        JSONObject json = getJson();
        PrintWriter out = null;
        try {
            JSONTokener tokener = null;
            JSONArray jsonArray = null;
            try {
                tokener = new JSONTokener(new FileReader("PlayerInfos.JSON"));
                jsonArray = new JSONArray(tokener);
            } catch (Exception e) {
                jsonArray = new JSONArray();
                System.err.println(e.getMessage());
            }
            jsonArray.put(this.playerInfo.getPlayerID(), json.toString());
            out = new PrintWriter(new BufferedWriter(new FileWriter("PlayerInfos.JSON")));
            out.append(jsonArray.toString());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }
  
    
}
