/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;
import java.util.Stack;

/**
 *
 * @author Samer2
 */
public class SmartRandomComputerPlayer extends ComputerPlayer implements Serializable{
	
    SmartRandomComputerPlayer(){
        this.currentStrategy = new SmartComputerStrategy();    
    }
    
    
    private  class SmartComputerStrategy extends AbstractComputerStrategy {
        public Stack<String> moves;
        private String lastMove;

        SmartComputerStrategy(){
            this.moves = new Stack<String>();
        }
        
        @Override
        protected BattleshipMove getNextMove() {
        	//boolean valid = true;
        
            if(this.push && lastMove!=null){
                int i=GameUtil.coordsToRow(lastMove),j=GameUtil.coordsToCol(lastMove);
                if(GameUtil.isValidCoords(i + 1, j))
                    moves.push(GameUtil.coordsToString( i + 1 , j));
                if(GameUtil.isValidCoords(i - 1, j))
                    moves.push(GameUtil.coordsToString( i - 1 , j));
                if(GameUtil.isValidCoords(i , j + 1))
                    moves.push(GameUtil.coordsToString( i , j + 1));
                if(GameUtil.isValidCoords(i , j - 1))
                    moves.push(GameUtil.coordsToString( i , j - 1));
                push = false;
            }
            if(moves.isEmpty()){
            	String coords;
            	System.out.println("here");
            	
               
                int i , j;
                i = GameUtil.getRandom();
                j = GameUtil.getRandom();
            
                coords = GameUtil.coordsToString(i, j);
                lastMove = coords;
                return new BattleshipMove(coords);
            	}
               
            
        	
            lastMove = (String)(moves.pop());
            return new BattleshipMove(lastMove);
        	
            
            
            
        }
        
        
    }

    
    
    
    
    
    
    
    
}
