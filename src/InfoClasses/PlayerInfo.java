/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoClasses;

import java.io.Serializable;

/**
 *
 * @author Samer2
 */
public class PlayerInfo implements Serializable{
	
    private int playerID;
    private String playerName;
    private int playedGames;
    private double mark;
    private int wins;

    public PlayerInfo() {
    	
    }
    

    public PlayerInfo(int playerID, String playerName, int playedGames, double mark) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playedGames = playedGames;
        this.mark = mark;
    }
    

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }


	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}
    
    
}
