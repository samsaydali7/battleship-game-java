/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonHandling;

import InfoClasses.GameInfo;
import java.io.BufferedWriter;
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
public class GameInfoHandler implements IJHandler{
    GameInfo gameInfo;

    public GameInfoHandler(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
    public JSONObject getJson(){
        JSONObject json = new JSONObject();
        json.put("gameID", gameInfo.getGameID());
        json.put("firstPlayer", gameInfo.getFirstPlayer());
        json.put("secondPlayer", gameInfo.getSecondPlayer());
        json.put("firstPlayerMark", gameInfo.getFirstPlayerMark());
        json.put("secondPlayerMark", gameInfo.getSecondPlayerMark());
        json.put("startingDate", gameInfo.getStartingDate().toString());
        json.put("endingDate", gameInfo.getEndingDate().toString());
        json.put("winner", gameInfo.getWinner());
        
        return json;
    }
    public void writeJsonToFile(){
        JSONObject json = getJson();
        PrintWriter out=null; 
        try{
            JSONTokener tokener = null;
            JSONArray jsonArray = null;
            try{
                tokener = new JSONTokener(new FileReader("GameInfos.JSON"));
                jsonArray = new JSONArray(tokener);
            }
            catch(Exception e){
                jsonArray = new JSONArray();
                System.err.println(e.getMessage());
            }
            jsonArray.put(this.gameInfo.getGameID(), json.toString());
            out = new PrintWriter(new BufferedWriter(new FileWriter("GameInfos.JSON")));
            out.append(jsonArray.toString());
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        finally{
            if(out!=null)
                out.close();
        }
    }
//    public GameInfo ReadGameInfoFromFile(){
//        GameInfo gameInfo= new GameInfo();
//        try{
//            JSONTokener tokener = new JSONTokener(new FileReader("GameInfo.JSON"));
//            JSONObject json = new JSONObject(tokener);
//            gameInfo.setGameID(json.getInt("gameID"));
//            gameInfo.setFirstPlayer(json.getString("firstPlayer"));
//            gameInfo.setSecondPlayer(json.getString("secondPlayer"));
//            gameInfo.setFirstPlayerMark(json.getInt("firstPlayerMark"));
//            gameInfo.setSecondPlayerMark(json.getInt("secondPlayerMark"));
//            gameInfo.setStartingDate(json.getString("startingDate"));
//            gameInfo.setEndingDate(json.getString("endingDate"));
//            gameInfo.setWinner(json.getString("winner"));
//        }catch(IOException ioe){
//            System.out.println(ioe.getMessage());
//        }finally{
//            return gameInfo;
//        }
//    }
}