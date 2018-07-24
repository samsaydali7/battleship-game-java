/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoClasses;

import BattleShipGamePackage.BattleshipGameSettings;
import BattleShipGamePackage.Grid;
import BattleShipGamePackage.Square;
import java.util.LinkedList;

/**
 *
 * @author Samer2
 */
public class GameSteps {
    private int gameID;
    private LinkedList<String> PlayersSteps; //a string goes like this "HH:mm:ss: PlayerName (i,j) HitResault"
    private Grid firstPlayerGrid;
    private Grid secondPlayerGrid;

    public GameSteps(int gameID, LinkedList<String> PlayersSteps, Grid firstPlayerGrid, Grid secondPlayerGrid) {
        this.gameID = gameID;
        this.PlayersSteps = PlayersSteps;
        this.firstPlayerGrid = firstPlayerGrid;
        this.secondPlayerGrid = secondPlayerGrid;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public LinkedList<String> getPlayersSteps() {
        return PlayersSteps;
    }

    public void setPlayersSteps(LinkedList<String> PlayersSteps) {
        this.PlayersSteps = PlayersSteps;
    }

    public Grid getFirstPlayerGrid() {
        return firstPlayerGrid;
    }

    public void setFirstPlayerGrid(Grid firstPlayerGrid) {
        this.firstPlayerGrid = new Grid();
        for(int i = 0;i<BattleshipGameSettings.getGridSize();i++)
            for(int j = 0 ; j<BattleshipGameSettings.getGridSize();j++){
                this.firstPlayerGrid.getSquare(i, j).setState(firstPlayerGrid.getSquare(i, j).getState());
            }
    }

    public Grid getSecondPlayerGrid() {
        return secondPlayerGrid;
    }

    public void setSecondPlayerGrid(Grid firstPlayerGrid) {
        this.secondPlayerGrid = new Grid();
        for(int i = 0;i<BattleshipGameSettings.getGridSize();i++)
            for(int j = 0 ; j<BattleshipGameSettings.getGridSize();j++){
                this.secondPlayerGrid.getSquare(i, j).setState(secondPlayerGrid.getSquare(i, j).getState());
            }
    }
    

    
}
