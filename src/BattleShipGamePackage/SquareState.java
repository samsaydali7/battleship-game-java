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
public enum SquareState implements Serializable{
     WATER,SHIP_PART,HIT,UNKNOWN,MINE,MINE_HIT,MISS;
    
    @Override
    public String toString() {
        String res;
        switch (this) {
            case WATER:
                res = "Water";
                break;
            case SHIP_PART:
                res = "SHIP_PART";
                break;
            case HIT:
                res = "HIT";
                break;
            case UNKNOWN:
                res = "UNKNOWN";
                break;
            case MINE:
                res = "MINE";
                break;
            case MINE_HIT:
                res = "MINE_HIT";
                break;
            case MISS:
            	res="MISS";
            	break;
            default:
                throw new AssertionError();
        }
        return res;
    }
    
    
    
    
    
}
