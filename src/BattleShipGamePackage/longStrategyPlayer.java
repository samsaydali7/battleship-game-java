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
public class longStrategyPlayer extends  ComputerPlayer implements Serializable{               
    
    longStrategyPlayer(){
        super();                                                              
        this.currentStrategy = new RandomComputerStrategy();
    }

    private class RandomComputerStrategy extends AbstractComputerStrategy implements Serializable{
        @Override
        protected BattleshipMove getNextMove() {
        	
            String coords;
            int i , j;
            i = GameUtil.getRandom();
            j = GameUtil.getRandom();
           
            
            
            coords = GameUtil.coordsToString(i, j);
          
            return new BattleshipMove(coords);
        }
        
        
    }



    
}
