/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;

/**
 *
 * @author Samer2
 */
public abstract class AbstractComputerStrategy implements Serializable{
    protected boolean push = false;
    protected abstract BattleshipMove getNextMove();

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }
    
    
}
