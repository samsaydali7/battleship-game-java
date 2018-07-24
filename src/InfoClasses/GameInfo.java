/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoClasses;



/**
 *
 * @author Samer2
 */
public class GameInfo {
    private int gameID;
    private String firstPlayer;
    private String secondPlayer;
    private double firstPlayerMark;
    private double secondPlayerMark;
    private String startingDate;
    private String endingDate;
    private String winner;

    public GameInfo(int gameID, String firstPlayer, String secondPlayer, double firstPlayerMark, double secondPlayerMark, String startingDate, String endingDate, String winner) {
        this.gameID = gameID;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerMark = firstPlayerMark;
        this.secondPlayerMark = secondPlayerMark;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.winner = winner;
    }

    public GameInfo() {
    }
    

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public double getFirstPlayerMark() {
        return firstPlayerMark;
    }

    public void setFirstPlayerMark(double firstPlayerMark) {
        this.firstPlayerMark = firstPlayerMark;
    }

    public double getSecondPlayerMark() {
        return secondPlayerMark;
    }

    public void setSecondPlayerMark(double secondPlayerMark) {
        this.secondPlayerMark = secondPlayerMark;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    
}
