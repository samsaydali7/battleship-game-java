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
public class Square implements Serializable{
    
    private SquareState state;
    
    public Square(){
        this.state = SquareState.UNKNOWN;
    }
    public Square(SquareState state){
        this.state = state;
    }
    public SquareState getState() {
        return state;
    }

    public void setState(SquareState state) {
        this.state = state;
    }
    
}
