/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleShipGamePackage;

import java.util.Random;

/**
 *
 * @author Samer2
 */
public class GameUtil {
    public static String coordsToString(int i,int j){
        char[] c = {(char)(i + '0'),(char) (j + BattleshipGameSettings.getGridSize() + 'A' - BattleshipGameSettings.getGridSize())};
        String s = new String(c);
        return s;
    }
    public static int coordsToCol(String s){
        return s.charAt(1) - 'A';
    }
    public static int coordsToRow(String s){
        return s.charAt(0) - '0';
    }
    public static int getRandom(){
                
        Random rand = new Random();

        return  rand.nextInt(BattleshipGameSettings.getGridSize()) + 0; //9 is the maximum and the 0 is our minimum 
    }
    
    public static int getMineMoveRandom(){
        
        Random rand = new Random();

        return  rand.nextInt(4) + 0; 
    }
    
    
    public static boolean isValidMineCoords(int i , int j ,Grid grid){

        if (i>=0 && j>=0 && i<BattleshipGameSettings.getGridSize() && j<BattleshipGameSettings.getGridSize())
            if((grid.getSquare(i, j).getState()==SquareState.WATER))
                return true;
        return false;
     }
    
    public static int getSleepTimeRandom() {
    	Random rand = new Random();
    	
    	return rand.nextInt(13) +1;
    	
    }
    public static boolean isValidCoords(String coords){
        int i , j;
        i = coordsToRow(coords);
        j = coordsToCol(coords);
        return (i>=0 && j>=0 && i<BattleshipGameSettings.getGridSize() && j<BattleshipGameSettings.getGridSize());
    }
    public static boolean isValidCoords(int i , int j){

        return (i>=0 && j>=0 && i<BattleshipGameSettings.getGridSize() && j<BattleshipGameSettings.getGridSize());
    }
}
