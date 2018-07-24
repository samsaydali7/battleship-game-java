/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.io.Serializable;
import java.util.LinkedList;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Samer2
 */
public class Mine implements Serializable{

    private Grid grid;
    int i, j;
    LinkedList<int[]> Pos = new  LinkedList<int[]>();
    
    

    public Mine(Grid grid) {
        this.grid = grid;
        InitMinePosition();
        
    }

    public void InitMinePosition() {
        int i=1, j=3;
        do {
           i = GameUtil.getRandom();
            j = GameUtil.getRandom();
        } while (grid.getSquare(i, j).getState() != SquareState.WATER);
        this.i = i;
        this.j = j;
        grid.SetState(i, j, SquareState.MINE);
    }
    public int getXCoordinate() {
    	
    	return j;
    	
    	
    }
    public int getYCoordinate() {
    	return i;
    	
    }
    public void updatePosition() {

        int a, b;
        if((!GameUtil.isValidMineCoords(this.i+1, this.j,grid))&&(!GameUtil.isValidMineCoords(this.i-1, this.j,grid))&&(!GameUtil.isValidMineCoords(this.i, this.j+1,grid))&&(!GameUtil.isValidMineCoords(this.i, this.j-1,grid))){
            return; 
        }
       
        int temp[] = {i,j};
        
        Pos.add(temp);
        
        grid.getSquare(this.i, this.j).setState(SquareState.WATER);
        
        switch (GameUtil.getMineMoveRandom()) {
            case 0:
                a = this.i + 1;
                b = this.j;
                break;
                
                
            case 1:
                a = this.i;
                b = this.j + 1;
                break;
                
            case 2:
                a = this.i;
                b = this.j - 1;
                break;
                
            case 3:
                a = this.i - 1;
                b = this.j;
                break;
                
            default:
                throw new AssertionError();
        }
        if (GameUtil.isValidCoords(a, b)) {
            if (grid.getSquare(a, b).getState() == SquareState.WATER) {
            	
                grid.SetState(a, b, SquareState.MINE);
                
              
              
              
             
                this.i=a;
                this.j=b;
                return;
            } else {
                updatePosition();
            }
        } else {
            updatePosition();
        }   
    }
    
    public void updatePosition2() {
    	 int a, b;
         if((!GameUtil.isValidMineCoords(this.i+1, this.j,grid))&&(!GameUtil.isValidMineCoords(this.i-1, this.j,grid))&&(!GameUtil.isValidMineCoords(this.i, this.j+1,grid))&&(!GameUtil.isValidMineCoords(this.i, this.j-1,grid))){
             return; 
         }
         
         int temp[] = {i,j};
         
         Pos.add(temp);
         
         grid.getSquare(this.i, this.j).setState(SquareState.WATER);
         
         do {
        	 switch (GameUtil.getMineMoveRandom()) {
             case 0:
                 a = this.i + 1;
                 b = this.j;
                 break;
                 
                 
             case 1:
                 a = this.i;
                 b = this.j + 1;
                 break;
                 
             case 2:
                 a = this.i;
                 b = this.j - 1;
                 break;
                 
             case 3:
                 a = this.i - 1;
                 b = this.j;
                 break;
                 
             default:
                 throw new AssertionError();
        	 
        	 
        	 
         }
        	 
         }
       while(! (GameUtil.isValidCoords(a, b) && (grid.getSquare(a, b).getState() == SquareState.WATER) ) );
    	
         grid.SetState(a, b, SquareState.MINE);
    	
         this.i=a;
         this.j=b;
         return;
    
    }
    public void endMine(){
        
    }
    
    public  LinkedList<int[]> getList(){
    	
    	return Pos;
    }
    
    
 
    
   
}
