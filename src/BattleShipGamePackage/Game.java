/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import Saving_loading.GameState;

/**
 *
 * @author Asus
 */
public abstract class Game implements Iplayable ,Serializable {
	
    protected LinkedList<Player> players;
    public int gameID;
    GameState currentGameState;
    protected String Winner = "You Lose!";
    
    protected LinkedList<String> steps;
    protected String startDate, endDate;
    
    protected Grid firstPlayerGrid;
    protected Grid secondPlayerGrid;
    
    
    
    abstract void  Start();
    abstract void  End();
    
    public Game(){
    	
        this.players = new LinkedList<Player>();
        this.steps = new LinkedList<String>();
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        this.startDate = sd.format(d).toString();
        
        
        assignGameId();
        currentGameState= new GameState();
        currentGameState.setgameId(gameID);
        
        
        
        
        
        
        
       
    }
  
    public void SetWinner(String winner) {
    	
    	Winner = winner;
    	
    }
   public GameState getCurrentGameState() {
    	return currentGameState;
    }
   private void assignGameId() {
       try {
           try (DataInputStream in = new DataInputStream(new FileInputStream("lastGameID.bin"))) {
               this.gameID = in.readInt() ;
           }
           try {
               DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("lastGameID.bin")));
               out.writeInt(this.gameID+1);
               out.close();
           } catch (IOException ioe) {
               System.err.println(ioe.getMessage());
           }
       } catch (IOException ioe) {
           System.out.println(ioe.getMessage());
           try {
               DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("lastGameID.bin")));
               out.writeInt(0);
               out.close();
           } catch (IOException iooe) {
               System.out.println(iooe.getMessage());
           }

       }
   }
public LinkedList<Player> getPlayers() {
	return players;
}
public void setPlayers(LinkedList<Player> players) {
	this.players = players;
}

public LinkedList<String> getSteps() {
    return steps;
}
public void setFirstPlayerGrid(Grid firstPlayerGrid) {
	 this.firstPlayerGrid = new Grid();
     for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
         for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
             this.firstPlayerGrid.getSquare(i, j).setState(firstPlayerGrid.getSquare(i, j).getState());
         }
     }
}
public void setSecondPlayerGrid(Grid secondPlayerGrid) {
	this.secondPlayerGrid = new Grid();
    for (int i = 0; i < BattleshipGameSettings.getGridSize(); i++) {
        for (int j = 0; j < BattleshipGameSettings.getGridSize(); j++) {
            this.secondPlayerGrid.getSquare(i, j).setState(secondPlayerGrid.getSquare(i, j).getState());
        }
    }
}






    
}
