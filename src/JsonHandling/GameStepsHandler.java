/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonHandling;

import BattleShipGamePackage.BattleshipGameSettings;
import InfoClasses.GameSteps;
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
public class GameStepsHandler implements IJHandler{
    private GameSteps gameSteps;

    public GameStepsHandler(GameSteps gameSteps) {
        this.gameSteps = gameSteps;
    }

    @Override
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        JSONArray firstPlayerGrid = new JSONArray();
        JSONArray secondPlayerGrid = new JSONArray();
        JSONArray playersSteps = new JSONArray();

        json.put("gameID", gameSteps.getGameID());

        for(int i=0;i<BattleshipGameSettings.getGridSize();i++){
            JSONArray line = new JSONArray();
            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
                line.put(j, gameSteps.getFirstPlayerGrid().getSquare(i, j).getState().toString());
            }
            firstPlayerGrid.put(i,line);
        }
        json.put("firstPlayerGrid", firstPlayerGrid);

        for(int i=0;i<BattleshipGameSettings.getGridSize();i++){
            JSONArray line = new JSONArray();
            for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
                line.put(j, gameSteps.getSecondPlayerGrid().getSquare(i, j).getState().toString());
            }
            secondPlayerGrid.put(i,line);
        }
        json.put("secondPlayerGrid", secondPlayerGrid);
        int i=0;
        for (String playersStep : gameSteps.getPlayersSteps()) {
            playersSteps.put(i, playersStep);
            i++;
        }
        json.put("playersSteps", playersSteps);
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
                tokener = new JSONTokener(new FileReader("GameSteps.JSON"));
                jsonArray = new JSONArray(tokener);
            } catch (Exception e) {
                jsonArray = new JSONArray();
                System.err.println(e.getMessage());
            }
            jsonArray.put(this.gameSteps.getGameID(), json.toString());
            out = new PrintWriter(new BufferedWriter(new FileWriter("GameSteps.JSON")));
            out.append(jsonArray.toString());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public GameSteps getGameSteps() {
        return gameSteps;
    }

    public void setGameSteps(GameSteps gameSteps) {
        this.gameSteps = gameSteps;
    }
    
    

   
}
