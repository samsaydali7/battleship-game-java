/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public abstract  class Player implements Serializable{
    protected Iplayable currentGame;

    abstract void SupscripeTo(Iplayable game) throws PlayerNotCompatableException;
    abstract void LeaveGame();
    abstract void  Init();
    
    public Game returnGame() {
    	
    	return (Game) currentGame;
    }
    
}
