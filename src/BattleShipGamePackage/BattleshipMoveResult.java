/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.util.LinkedList;

/**
 *
 * @author Asus
 */
public class BattleshipMoveResult {
    String coords;
    SquareState squareState;
    String notfication;
    private LinkedList<int[]> hits;
    
    BattleshipMoveResult(String coords,SquareState squareState,String note){
        this.coords = coords;
        this.squareState = squareState;
        this.notfication = note;
    }
    
    BattleshipMoveResult(String coords,SquareState squareState,String note,LinkedList<int[]> hits){
        this.coords = coords;
        this.squareState = squareState;
        this.notfication = note;
        this.hits = hits;
    }
    public LinkedList<int[]> getHits() {
        return hits;
    }
    public void setCoords(String coords) {
        this.coords = coords;
    }
    

    public void setSquareState(SquareState squareState) {
        this.squareState = squareState;
    }

	public String getCoords() {
		return coords;
	}
	public SquareState getSquareState() {
		return squareState;
	}
	public String getNotfication() {
		return notfication;
	}
    
	 public void setNotfication(String notfication) {
	        this.notfication = notfication;
	    }
    
    
}
